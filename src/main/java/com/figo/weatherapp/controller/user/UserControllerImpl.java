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


    @Override
    public Mono<ApiResult<UserDTO>> getApplicationUserMe(ServerWebExchange exchange) {
        return userService.getApplicationUserMe(exchange);
    }

    @Override
    public Mono<Mono<ApiResult<String>>> editUserSelf(UserEditDTO userEditDTO , ServerWebExchange exchange) {
        return userService.editUserSelf(userEditDTO , exchange);
    }

    @Override
    public Mono<ApiResult<TokenDTO>> resetPassword(ServerWebExchange exchange, ResetPasswordDTO resetPasswordDTO) {
        return userService.resetPassword(resetPasswordDTO, exchange);
    }

    @Override
    public Mono<ApiResult<SubscriptionResponse>> subscribeCityWeather(String id, ServerWebExchange exchange) {
        return userService.subscribeCityWeather(id, exchange);
    }

    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getSubscribedCitiesWeather(ServerWebExchange exchange) {
        return userService.getSubscribedCitiesWeather(exchange);
    }

    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getAllCities(ServerWebExchange exchange) {
        return userService.getAllCities();
    }

    @Override
    public Mono<ApiResult<String>> removeSubscription(Integer cityId, ServerWebExchange exchange) {
        return userService.removeSubscription(cityId, exchange);
    }


}
