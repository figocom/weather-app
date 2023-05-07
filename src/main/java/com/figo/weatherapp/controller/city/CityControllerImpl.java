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
    /**
     * The getCities function returns a Flux of ApiResult&lt;List&lt;CityWeatherDto&gt;&gt;.
     * The function calls the cityService's getCities function, which returns a Flux of ApiResult&lt;List&lt;CityWeatherDto&gt;&gt;.

     *
     *
     * @return A flux&lt;apiresult&lt;list&lt;cityweatherdto&gt;&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getCities() {
        return cityService.getCities();
    }

    /**
     * The getCitiesWithDisabled function is a function that returns the list of cities with disabled weather.
     *
     * @return A flux of apiresult&lt;list&lt;cityweatherdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getCitiesWithDisabled() {
        return cityService.getCitiesWithDisabled();
    }

    /**
     * The getCityById function is a function that takes in an id and returns the city with that id.
     *
     * @param id id Get the city by its id
     *
     * @return The mono&lt;apiresult&lt;citydto&gt;&gt; type
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> getCityById(String id) {
        return cityService.getCityById(id);
    }

    /**
     * The updateCityWeather function is used to update the weather of a city.
     *
     * @param id id Identify the city to be updated
     *
     * @return A mono&lt;apiresult&lt;citydto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> updateCityWeather(String id) {

        return cityService.updateCityWeather(id);
    }

    /**
     * The updateCityWeatherAll function is a function that updates the weather for all cities in the database.
     *
     * @return A mono&lt;apiresult&lt;list&lt;citydto&gt;&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<List<CityDTO>>> updateCityWeatherAll() {

        return cityService.updateCityWeatherAll();
    }

    /**
     * The updateCity function is used to update a city in the database.
     *
     * @param id id Identify the city to be deleted

     * @param cityCreatedDTO cityCreatedDTO Pass the data from the request body to the service
     * @param exchange exchange Get the request and response objects
     *
     * @return A mono&lt;apiresult&lt;citydto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> updateCity(String id, CityCreatedDTO cityCreatedDTO , ServerWebExchange exchange) {

        return cityService.updateCity(id, cityCreatedDTO, exchange);
    }

    /**
     * The createCity function is used to create a new city.
     *
     * @param cityCreatedDTO cityCreatedDTO Create a new city
     *
     * @return A mono&lt;apiresult&lt;citydto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> createCity(CityCreatedDTO cityCreatedDTO) {

        return cityService.createCity(cityCreatedDTO);
    }

    /**
     * The updateCityWeatherManual function is used to update the weather of a city manually.
     *
     * @param id id Identify the city to be updated
     * @param cityDTO cityDTO Get the city name  from the request body
     *
     * @return An apiresult&lt;citydto&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> updateCityWeatherManual(String id , CityDTO cityDTO) {

        return cityService.updateCityWeatherManual(id,cityDTO);
    }
}
