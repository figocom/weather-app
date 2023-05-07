package com.figo.weatherapp.controller.user;


import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import com.figo.weatherapp.utils.AppConstant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

/**
 * UserNING MALUMOTLARINI TAHRIRLAMOQCHI VA O'CHIRMOQCHI BO'LSA HAMDA <p>
 * TIZIMDAGI USER O'ZINING MALUMOTLARINI VA PAROLINI TAHRIRLAMOQCHI BO'LSA, VA<p>
 * O'ZI TO'G'RISIDAGI MA'LUMOTLARNI
 */
@RequestMapping(path = UserController.USER_CONTROLLER_PATH)
public interface UserController {

    String USER_CONTROLLER_PATH = AppConstant.BASE_PATH_V1 + "user/";

    String USER_APPLICATION_ME_PATH = "application/me";
    String LOGOUT_PATH = "logout";
    String EDIT_USER_SELF_PATH_PATH = "edit-self";
    String RESET_PASSWORD_PATH = "reset-password";
    String Get_All_cities="get-all-cities";
    String Subscribe_City_Weather = "subscribe-city-weather/{id}";
    String Get_subscribed_Cities_Weather = "get-subscribed-cities-weather";
    String Remove_Subscription="remove-subscription/{cityId}";

    /**
     * TIZIMDAGI (YA`NI SECURITY_CONTEXTDAGI) USER O`ZINING MALUMOTLARINI OLISHI
     *
     * @return
     */
    @GetMapping(path = USER_APPLICATION_ME_PATH)
    Mono<ApiResult<UserDTO>> getApplicationUserMe(ServerWebExchange exchange);


    /**
     * USER O'ZINI EDIT QILSA SHU YO'LGA KELADI
     */

    @PutMapping(EDIT_USER_SELF_PATH_PATH)
    Mono<Mono<ApiResult<String>>> editUserSelf(@RequestBody @Valid UserEditDTO userEditDTO , ServerWebExchange exchange);


    @PostMapping(path = RESET_PASSWORD_PATH)
    Mono<ApiResult<TokenDTO>> resetPassword(ServerWebExchange exchange, @Valid @RequestBody ResetPasswordDTO resetPasswordDTO);
    @PostMapping(Subscribe_City_Weather)
    Mono<ApiResult<SubscriptionResponse>> subscribeCityWeather(@PathVariable String id, ServerWebExchange exchange);
    @GetMapping(Get_subscribed_Cities_Weather)
    Flux<ApiResult<List<CityWeatherDto>>> getSubscribedCitiesWeather(ServerWebExchange exchange);
    @GetMapping(Get_All_cities)
    Flux<ApiResult<List<CityWeatherDto>>> getAllCities(ServerWebExchange exchange);
    @DeleteMapping(Remove_Subscription)
    Mono<ApiResult<String>>removeSubscription(@PathVariable Integer cityId, ServerWebExchange exchange);







}
