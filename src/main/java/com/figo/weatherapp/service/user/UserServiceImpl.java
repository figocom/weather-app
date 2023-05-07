package com.figo.weatherapp.service.user;

import com.figo.weatherapp.entity.AuthUser;
import com.figo.weatherapp.entity.City;
import com.figo.weatherapp.entity.Subscription;
import com.figo.weatherapp.exception.RestException;
import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import com.figo.weatherapp.repository.AuthUserRepository;
import com.figo.weatherapp.repository.CityRepository;
import com.figo.weatherapp.repository.SubscriptionRepository;
import com.figo.weatherapp.security.JwtTokenProvider;
import com.figo.weatherapp.service.city.CityService;
import com.figo.weatherapp.utils.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthUserRepository authUserRepository;
    private final CityService cityService;
    private final SubscriptionRepository subscriptionRepository;
    private final CityRepository cityRepository;

    public UserServiceImpl(JwtTokenProvider jwtTokenProvider, AuthUserRepository authUserRepository, CityService cityService, SubscriptionRepository subscriptionRepository, CityRepository cityRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authUserRepository = authUserRepository;
        this.cityService = cityService;
        this.subscriptionRepository = subscriptionRepository;
        this.cityRepository = cityRepository;
    }

    /**
     * The resetPassword function is used to reset a user's password.
     * @param resetPasswordDTO resetPasswordDTO Get the old password, new password and confirm new password from the request body
     * @param exchange exchange Gets the token from the header
     *
     * @return A mono&lt;apiresult&lt;tokendto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<TokenDTO>> resetPassword(ResetPasswordDTO resetPasswordDTO, ServerWebExchange exchange) {

        if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmNewPassword())){
            return Mono.error(RestException.badRequest("password not match"));
        }
        UserDetails principal = jwtTokenProvider.getUserDetails(exchange);
        if (Objects.nonNull(principal)){
        return authUserRepository.
                    findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(principal.getUsername()).
                    switchIfEmpty(Mono.error(RestException.notFound(principal.getUsername()))).
                    flatMap(authUser -> {
                        if (!authUser.getPassword().equals(resetPasswordDTO.getOldPassword())){
                            return Mono.error(RestException.badRequest("old password not match"));
                        }
                        authUser.setPassword(resetPasswordDTO.getNewPassword());
                        return authUserRepository.save(authUser);
                    }).
                    flatMap(authUser1 -> Mono.just(ApiResult.successResponse(new TokenDTO(AppConstant.AUTHORIZATION_HEADER_PREFIX,jwtTokenProvider.createToken(authUser1)))));
        }
        return Mono.error(RestException.badRequest("token not found"));
    }

    /**
     * The getApplicationUserMe function is used to retrieve the user's information from the database.
     * @param exchange exchange Gets the token from the request header
     *
     * @return A mono&lt;apiresult&lt;userdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<UserDTO>> getApplicationUserMe(ServerWebExchange exchange) {

        UserDetails userDetails = jwtTokenProvider.getUserDetails(exchange);
        UserDTO userDTO=new UserDTO();

        Mono<AuthUser> user =
                authUserRepository.findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(userDetails.getUsername());
        return user.flatMap(
                authUser -> {
                    userDTO.setId(authUser.getId());
                    userDTO.setFirstName(authUser.getFirstName());
                    userDTO.setLastName(authUser.getLastName());
                    userDTO.setEnabled(authUser.isEnabled());
                    return Mono.just(ApiResult.successResponse(userDTO));
                }

        ).switchIfEmpty(Mono.error(RestException.forbidden()));

    }

    /**
     * The editUserSelf function is used to edit the user's own information.

     * @param userEditDTO userEditDTO Get the user's id, first name, last name and current password
     * @param exchange exchange Gets the user's token from the request header

     *
     * @return A mono&lt;mono&lt;apiresult&lt;string&gt;&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<Mono<ApiResult<String>>> editUserSelf(UserEditDTO userEditDTO, ServerWebExchange exchange) {

        Mono<AuthUser> user = authUserRepository.findFirstByIdAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(userEditDTO.getId());
        return user.flatMap(
                authUser -> {
                    if (!authUser.getPassword().equals(userEditDTO.getCurrentPassword())) return Mono.error(RestException.badRequest("password dont match"));
                    authUser.setFirstName(userEditDTO.getFirstName());
                    authUser.setLastName(userEditDTO.getLastName());
                    authUser.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC())));
                    return authUserRepository.save(authUser).thenReturn(
                    Mono.just(ApiResult.successResponse(authUser.getUsername())));
                }).switchIfEmpty(Mono.error(RestException.notFound("user")));
    }

    /**
     * The subscribeCityWeather function subscribes a user to a city's weather.
     * @param id id Get the city id from the url
     * @param exchange exchange Gets the user details from the token
     *
     * @return A mono&lt;apiresult&lt;subscriptionresponse&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<SubscriptionResponse>> subscribeCityWeather(String id, ServerWebExchange exchange) {

        Mono<City> cityMono = cityRepository.findById(Integer.valueOf(id)).switchIfEmpty(Mono.error(RestException.notFound("city")));
        UserDetails userDetails = jwtTokenProvider.getUserDetails(exchange);
        Mono<AuthUser> user =
                authUserRepository.findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(userDetails.getUsername());
        return user.flatMap(authUser->subscriptionRepository.findAllByUserId(authUser.getId()).collectList().flatMap(subscriptions -> {
            if (subscriptions.stream().map(Subscription::getCityId).collect(Collectors.toList()).contains(Integer.valueOf(id))){
                return Mono.error(RestException.badRequest("already subscribed"));
            }
            Mono<Subscription> subscriptionMono = cityMono.flatMap(city -> subscriptionRepository.save(new Subscription(authUser.getId(), city.getId(), true)));
            return subscriptionMono.flatMap(subscription -> cityMono.flatMap(city -> Mono.just(ApiResult.successResponse(new SubscriptionResponse( city.getName() , userDetails.getUsername())))));
        })).switchIfEmpty(Mono.error(RestException.badRequest("user not found")));
    }

    /**
     * The getSubscribedCitiesWeather function is a function that returns the weather of all cities that are subscribed to by the user.
     * @param exchange exchange Gets the user's jwt token from the request header
     *
     * @return A flux of apiresult&lt;list&lt;cityweatherdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getSubscribedCitiesWeather(ServerWebExchange exchange) {

        UserDetails userDetails = jwtTokenProvider.getUserDetails(exchange);
        Mono<AuthUser> user =
                authUserRepository.findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(userDetails.getUsername());
        Flux<Subscription> subscriptionFlux = user.flatMapMany(authUser -> subscriptionRepository.findAllByUserId(authUser.getId()));
        Flux<City> cityFlux = subscriptionFlux.collectList().flatMapMany(subscription -> cityRepository.findAllById(subscription.stream().map(Subscription::getCityId).collect(Collectors.toList())));
        return cityFlux.collectList().flatMapMany(cities -> {
            List<CityWeatherDto> cityWeatherDtos = CityWeatherDto.fromEntity(cities);
            return Flux.just(ApiResult.successResponse(cityWeatherDtos));
        });

    }

    /**
     * The getAllCities function is a function that returns all the cities in the database.
     * @return A flux of apiresult&lt;list&lt;cityweatherdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getAllCities() {

        return cityService.getCities();
    }

    /**
     * The removeSubscription function removes a subscription from the database.
     * @param cityId cityId Find the subscription in the database
     * @param exchange exchange Retrieves the user's jwt token from the request header
     *
     * @return A mono&lt;apiresult&lt;string&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<String>> removeSubscription(Integer cityId, ServerWebExchange exchange) {

        UserDetails userDetails = jwtTokenProvider.getUserDetails(exchange);
        Mono<AuthUser> user =
                authUserRepository.findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(userDetails.getUsername());
        Mono<Subscription> subscriptionMono = user.flatMap(authUser -> subscriptionRepository.findFirstByUserIdAndCityId(authUser.getId(), cityId));
        Mono<Void> subscription1 = subscriptionMono.switchIfEmpty(Mono.error(RestException.notFound("subscription"))).flatMap(subscriptionRepository::delete);
        return subscription1.then(Mono.just(ApiResult.successResponse("subscription deleted")));
    }
}
