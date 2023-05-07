package com.figo.weatherapp.service.user;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserService {

    Mono<ApiResult<TokenDTO>> resetPassword(ResetPasswordDTO resetPasswordDTO , ServerWebExchange exchange);

    Mono<ApiResult<UserDTO>> getApplicationUserMe(ServerWebExchange exchange);

    Mono<Mono<ApiResult<String>>> editUserSelf(UserEditDTO userEditDTO, ServerWebExchange exchange);

    Mono<ApiResult<SubscriptionResponse>> subscribeCityWeather(String id, ServerWebExchange exchange);

    Flux<ApiResult<List<CityWeatherDto>>> getSubscribedCitiesWeather(ServerWebExchange exchange);

    Flux<ApiResult<List<CityWeatherDto>>> getAllCities();

    Mono<ApiResult<String>> removeSubscription(Integer cityId, ServerWebExchange exchange);
}
