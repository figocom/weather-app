package com.figo.weatherapp.controller.user;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import com.figo.weatherapp.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;


    /**
     * The getApplicationUserMe function is used to get the current user's information.
     *
     * @param exchange exchange Get the user's access token from the request header
     *
     * @return A mono&lt;apiresult&lt;userdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<UserDTO>> getApplicationUserMe(ServerWebExchange exchange) {

        return userService.getApplicationUserMe(exchange);
    }

    /**
     * The editUserSelf function is used to edit the user's own information.
     *
     * @param userEditDTO userEditDTO Get the user's new information
     * @param exchange exchange Get the current request
     *
     * @return A mono&lt;mono&lt;apiresult&lt;string&gt;&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<Mono<ApiResult<String>>> editUserSelf(UserEditDTO userEditDTO , ServerWebExchange exchange) {

        return userService.editUserSelf(userEditDTO , exchange);
    }

    /**
     * The resetPassword function is used to reset a user's password.
     *
     * @param exchange exchange Get the request and response
     * @param resetPasswordDTO resetPasswordDTO Get the email and password from the request body
     *
     * @return A mono&lt;apiresult&lt;tokendto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<TokenDTO>> resetPassword(ServerWebExchange exchange, ResetPasswordDTO resetPasswordDTO) {

        return userService.resetPassword(resetPasswordDTO, exchange);
    }

    /**
     * The subscribeCityWeather function subscribes a user to the weather of a city.
     *
     * @param id id Identify the city to be unsubscribed from
     * @param exchange exchange Get the user's access token from the request header
     *
     * @return A mono&lt;apiresult&lt;subscriptionresponse&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<SubscriptionResponse>> subscribeCityWeather(String id, ServerWebExchange exchange) {

        return userService.subscribeCityWeather(id, exchange);
    }

    /**
     * The getSubscribedCitiesWeather function is a function that returns the weather of all cities subscribed to by the user.
     *
     * @param exchange exchange Get the user token from the request
     *
     * @return A flux of apiresult&lt;list&lt;cityweatherdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getSubscribedCitiesWeather(ServerWebExchange exchange) {

        return userService.getSubscribedCitiesWeather(exchange);
    }

    /**
     * The getAllCities function is a function that returns all the cities in the database.
     *
     *
     * @param exchange exchange Get the user's ip address
     *
     * @return A flux&lt;apiresult&lt;list&lt;cityweatherdto&gt;&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getAllCities(ServerWebExchange exchange) {

        return userService.getAllCities();
    }

    /**
     * The removeSubscription function removes a subscription from the user's list of subscriptions.
     *
     *
     * @param cityId cityId Identify the city that is to be removed from the user's subscription list
     * @param exchange exchange Get the user's session
     *
     * @return A mono&lt;apiresult&lt;string&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<String>> removeSubscription(Integer cityId, ServerWebExchange exchange) {

        return userService.removeSubscription(cityId, exchange);
    }


}
