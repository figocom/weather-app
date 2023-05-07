package com.figo.weatherapp.controller.city;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.CityCreatedDTO;
import com.figo.weatherapp.payload.CityDTO;
import com.figo.weatherapp.payload.CityWeatherDto;
import com.figo.weatherapp.utils.AppConstant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = CityController.CITY_CONTROLLER_PATH)
public interface CityController {
    String CITY_CONTROLLER_PATH = AppConstant.BASE_PATH_V1 + "city/";
    String GET_ALL_CITY_PATH = "get-all-city";
    String GET_ALL_CITY_WITH_DISABLED= "get-all-city-with-disabled";
    String GET_CITY_BY_ID_PATH = "get-city-by-id/{id}";
    String CREATE_CITY_PATH = "create-city";
    String UPDATE_CITY_PATH = "update-city/{id}";
    String Update_City_Weather = "update-city-weather/{id}";
    String Update_City_Weather_Manual = "update-city-weather-manual/{id}";

    String Update_City_Weather_All = "update-city-weather-all";
    @GetMapping(GET_ALL_CITY_PATH)
    Flux<ApiResult<List<CityWeatherDto>>> getCities();
    @GetMapping(GET_ALL_CITY_WITH_DISABLED)
    Flux<ApiResult<List<CityWeatherDto>>> getCitiesWithDisabled();
    @GetMapping(GET_CITY_BY_ID_PATH)
    Mono<ApiResult<CityDTO>> getCityById(@PathVariable String id);
    @PatchMapping(Update_City_Weather)
    Mono<ApiResult<CityDTO>> updateCityWeather(@PathVariable String id);
    @PatchMapping(Update_City_Weather_All)
    Mono<ApiResult<List<CityDTO>>> updateCityWeatherAll();
    @PatchMapping(UPDATE_CITY_PATH)
    Mono<ApiResult<CityDTO>> updateCity(@PathVariable String id, @Valid @RequestBody CityCreatedDTO cityCreatedDTO, ServerWebExchange exchange);
    @PostMapping(CREATE_CITY_PATH)
    Mono<ApiResult<CityDTO>> createCity(@Valid @RequestBody CityCreatedDTO cityCreatedDTO);
    @PatchMapping(Update_City_Weather_Manual)
    Mono<ApiResult<CityDTO>> updateCityWeatherManual(@PathVariable String id, @Valid @RequestBody CityDTO cityDTO);




}
