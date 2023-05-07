package com.figo.weatherapp.service.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.figo.weatherapp.entity.City;
import com.figo.weatherapp.exception.RestException;
import com.figo.weatherapp.feign.FeignService;
import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.CityCreatedDTO;
import com.figo.weatherapp.payload.CityDTO;
import com.figo.weatherapp.payload.CityWeatherDto;
import com.figo.weatherapp.repository.AuthUserRepository;
import com.figo.weatherapp.repository.CityRepository;
import com.figo.weatherapp.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{
    private final CityRepository cityRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthUserRepository authUserRepository;
    private final FeignService feignService;
    private final ObjectMapper objectMapper;
    /**
     * The getCities function returns a Flux of ApiResult&lt;List&lt;CityWeatherDto&gt;&gt;.
     * The function first fetches all cities from the database that are enabled, and then maps each city to an ApiResult containing a singleton list of CityWeatherDto objects.
     * If no cities are found, it will return an empty list instead.
     * @return A flux of apiresult&lt;list&lt;cityweatherdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getCities() {

        return cityRepository.findAllByEnabledTrue()
                .map(city -> ApiResult.successResponse(Collections.singletonList(CityWeatherDto.fromEntity(city))))
                        .defaultIfEmpty(ApiResult.successResponse(Collections.emptyList()));
    }

    /**
     * The getCityById function returns a Mono&lt;ApiResult&lt;CityDTO&gt;&gt;.
     * The getCityById function takes in an id as a String and uses it to find the city with that id. If the city is found, then it will return an ApiResult containing that CityDTO object. Otherwise, if no such city exists, then it will throw a RestException saying &quot;City not found&quot;.
     * @param id id Get the city by id
     * @return A mono&lt;apiresult&lt;citydto&gt;&gt;
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> getCityById(String id) {

        return cityRepository.findById(Integer.valueOf(id))
                .map(city -> ApiResult.successResponse(CityDTO.fromEntity(city)))
                .switchIfEmpty(Mono.error(RestException.notFound("City not found")));
    }

    /**
     * The updateCityWeather function is a function that takes in an id of a city and updates the weather for that city.
     * It does this by first finding the City object with the given id, then it uses feign to get the weather from openweathermap.org
     * Then it edits and saves that City object with its new weather information. Finally, it returns an ApiResult containing
     * either success or error depending on whether or not there was an error updating/saving/finding etc...
     * @param id id Find the city in the database
     *
     * @return A mono&lt;apiresult&lt;citydto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> updateCityWeather(String id) {

        Mono<City> cityMono = cityRepository.findById(Integer.valueOf(id)).switchIfEmpty(Mono.error(RestException.notFound("City not found")));
        Mono<String> stringMono = cityMono.flatMap(city -> feignService.getWeather(city.getName()));
        Mono<City> editedCity = stringMono.flatMap(
                s -> getAndEditCityMono(cityMono, s)
        );
        return editedCity.map(city -> ApiResult.successResponse(CityDTO.fromEntity(city)));

    }


    /**
     * The updateCityWeatherAll function is a function that updates the weather of all cities in the database.
     * It does this by first finding all cities in the database, then for each city it finds, it calls an external API to get its weather.
     * Then, once we have both a City and its corresponding Weather data from our external API call (in String form), we pass them into another function called getAndEditCityMono which will edit our City object with new Weather data and return us a Mono&lt;City&gt; object containing that edited city.
     * We then collect these Mono&lt;City&gt; objects into one List&lt;Mono&lt;City
     * @return A mono&lt;apiresult&lt;list&lt;citydto&gt;&gt;&gt;
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<List<CityDTO>>> updateCityWeatherAll() {
        return cityRepository.findAll()
                .flatMap(city -> {
                    Mono<String> stringMono = feignService.getWeather(city.getName());
                    return stringMono.flatMap(
                            s -> getAndEditCityMono(Mono.just(city), s));
                }).collectList().
                map(cities -> ApiResult.successResponse(Collections.singletonList(CityDTO.fromEntity(cities.get(0)))));
    }

    /**
     * The updateCity function is used to update a city in the database.
     * @param id id Identify the city to be deleted
     * @param cityCreatedDTO cityCreatedDTO Pass the data from the request body to this function
     * @param exchange exchange Get the request headers
     *
     * @return A mono&lt;apiresult&lt;citydto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> updateCity(String id, CityCreatedDTO cityCreatedDTO , ServerWebExchange exchange) {

        return cityRepository.existsById(Integer.valueOf(id))
                .flatMap(city -> {
                    if(!city)  {return Mono.error(RestException.badRequest("City not exists"));}
                    return cityRepository.findById(Integer.valueOf(id)).flatMap(city1 -> {
                        city1.setName(cityCreatedDTO.getName());
                        city1.setEnabled(cityCreatedDTO.isEnabled());
                        city1.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                        return cityRepository.save(city1);
                    });
                }).thenReturn(ApiResult.successResponse(CityDTO.fromEntity(cityCreatedDTO.toEntity())));

    }

    /**
     * The createCity function creates a new city in the database.
     * @param cityCreatedDTO cityCreatedDTO Create a new city
     *
     * @return A mono&lt;apiresult&lt;citydto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<CityDTO>> createCity(CityCreatedDTO cityCreatedDTO) {

        return cityRepository.existsByName(cityCreatedDTO.getName()).
                flatMap(city -> {if(city)  {return Mono.error(RestException.badRequest("City already exists"));}
                else return cityRepository.save(cityCreatedDTO.toEntity()).
                            thenReturn(ApiResult.successResponse(CityDTO.fromEntity(cityCreatedDTO.toEntity())));});
                      

    }

    /**
     * The getCitiesWithDisabled function returns a Flux of ApiResult&lt;List&lt;CityWeatherDto&gt;&gt;.
     * The function uses the cityRepository to find all cities in the database, and then maps each city into a CityWeatherDto object.
     * If there are no cities found, an empty list is returned instead.
     * @return A flux of apiresult&lt;list&lt;cityweatherdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getCitiesWithDisabled() {

        return cityRepository.findAll()
                .map(city -> ApiResult.successResponse(Collections.singletonList(CityWeatherDto.fromEntity(city))))
                        .defaultIfEmpty(ApiResult.successResponse(Collections.emptyList()));
    }

    /**
     * The updateCityWeatherManual function is used to manually update the weather of a city.
     * @param id id Find the city by id
     * @param cityDTO cityDTO Get the values from the request body
     *
     * @return A mono&lt;apiresult&lt;citydto&gt;&gt;
     *
     * @docauthor Trelent
     */
    @Override
    public Mono<ApiResult<CityDTO>> updateCityWeatherManual(String id , CityDTO cityDTO) {

        return cityRepository.findById(Integer.valueOf(id)).switchIfEmpty(Mono.error(RestException.notFound("City not found")))
                .flatMap(city -> {
                    if (cityDTO.getTemperature_celsius() != null) city.setTemperature_celsius(cityDTO.getTemperature_celsius());
                    if (cityDTO.getTemperature_fahrenheit() != null) city.setTemperature_fahrenheit(cityDTO.getTemperature_fahrenheit());
                    if (cityDTO.getWindSpeedKmh() != null) city.setWindSpeedKmh(cityDTO.getWindSpeedKmh());
                    if (cityDTO.getWindSpeedMph() != null) city.setWindSpeedMph(cityDTO.getWindSpeedMph());
                    return cityRepository.save(city);
                }).map(city -> ApiResult.successResponse(CityDTO.fromEntity(city)));
    }

    /**
     * The getAndEditCityMono function takes a Mono&lt;City&gt; and a String as parameters.
     * The function returns a Mono&lt;City&gt;.
     * The getAndEditCityMono function is used to edit the City object in the database with new data from OpenWeatherMap.org's API.

     *
     * @param cityMono&lt;City&gt; cityMono Get the city from the database
     * @param s s Get the json data from the api
     *
     * @return A mono&lt;city&gt; object
     *
     * @docauthor Manguberdi
     */
    private Mono<City> getAndEditCityMono(Mono<City> cityMono, String s) {

        JsonNode jsonNode;
        CityDTO resultFROM;
        try {
            jsonNode = objectMapper.readValue(s, JsonNode.class);
            JsonNode current = jsonNode.get("current");
            resultFROM = objectMapper.readValue(current.toString(), new TypeReference<CityDTO>() {
            });
        } catch (JsonProcessingException e) {
            return Mono.error(new RuntimeException(e));
        }
        return cityMono.flatMap(
                city -> {
                    city.setTemperature_celsius(resultFROM.getTemperature_celsius());
                    city.setTemperature_fahrenheit(resultFROM.getTemperature_fahrenheit());
                    city.setWindSpeedKmh(resultFROM.getWindSpeedKmh());
                    city.setWindSpeedMph(resultFROM.getWindSpeedMph());
                    city.setWindDirection(resultFROM.getWindDirection());
                    city.setHumidity(resultFROM.getHumidity());
                    city.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                    return cityRepository.save(city);
                }
        );
    }
}
