package com.figo.weatherapp.service.city;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.CityCreatedDTO;
import com.figo.weatherapp.payload.CityDTO;
import com.figo.weatherapp.payload.CityWeatherDto;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CityService {
    Flux<ApiResult<List<CityWeatherDto>>> getCities();

    Mono<ApiResult<CityDTO>> getCityById(String id);

    Mono<ApiResult<CityDTO>> updateCityWeather(String id);

    Mono<ApiResult<List<CityDTO>>> updateCityWeatherAll();


    Mono<ApiResult<CityDTO>> updateCity(String id, CityCreatedDTO cityCreatedDTO , ServerWebExchange exchange);

    Mono<ApiResult<CityDTO>> createCity(CityCreatedDTO cityCreatedDTO);

    Flux<ApiResult<List<CityWeatherDto>>> getCitiesWithDisabled();

    Mono<ApiResult<CityDTO>> updateCityWeatherManual(String id , CityDTO cityDTO);
}
