package com.figo.weatherapp.controller.city;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.CityCreatedDTO;
import com.figo.weatherapp.payload.CityDTO;
import com.figo.weatherapp.payload.CityWeatherDto;
import com.figo.weatherapp.service.city.CityService;
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
public class CityControllerImpl  implements CityController{
   private final CityService cityService;
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getCities() {
        return cityService.getCities();
    }

    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getCitiesWithDisabled() {
        return cityService.getCitiesWithDisabled();
    }

    @Override
    public Mono<ApiResult<CityDTO>> getCityById(String id) {
        return cityService.getCityById(id);
    }

    @Override
    public Mono<ApiResult<CityDTO>> updateCityWeather(String id) {
        return cityService.updateCityWeather(id);
    }

    @Override
    public Mono<ApiResult<List<CityDTO>>> updateCityWeatherAll() {
        return cityService.updateCityWeatherAll();
    }

    @Override
    public Mono<ApiResult<CityDTO>> updateCity(String id, CityCreatedDTO cityCreatedDTO , ServerWebExchange exchange) {
        return cityService.updateCity(id, cityCreatedDTO, exchange);
    }

    @Override
    public Mono<ApiResult<CityDTO>> createCity(CityCreatedDTO cityCreatedDTO) {
        return cityService.createCity(cityCreatedDTO);
    }

    @Override
    public Mono<ApiResult<CityDTO>> updateCityWeatherManual(String id , CityDTO cityDTO) {
        return cityService.updateCityWeatherManual(id,cityDTO);
    }
}
