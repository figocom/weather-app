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
    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getCities() {
        return cityRepository.findAllByEnabledTrue()
                .map(city -> ApiResult.successResponse(Collections.singletonList(CityWeatherDto.fromEntity(city))))
                        .defaultIfEmpty(ApiResult.successResponse(Collections.emptyList()));
    }

    @Override
    public Mono<ApiResult<CityDTO>> getCityById(String id) {
        return cityRepository.findById(Integer.valueOf(id))
                .map(city -> ApiResult.successResponse(CityDTO.fromEntity(city)))
                .switchIfEmpty(Mono.error(RestException.notFound("City not found")));
    }

    @Override
    public Mono<ApiResult<CityDTO>> updateCityWeather(String id) {
        Mono<City> cityMono = cityRepository.findById(Integer.valueOf(id)).switchIfEmpty(Mono.error(RestException.notFound("City not found")));
        Mono<String> stringMono = cityMono.flatMap(city -> feignService.getWeather(city.getName()));
        Mono<City> editedCity = stringMono.flatMap(
                s -> getAndEditCityMono(cityMono, s)
        );
        return editedCity.map(city -> ApiResult.successResponse(CityDTO.fromEntity(city)));

    }


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

    @Override
    public Mono<ApiResult<CityDTO>> createCity(CityCreatedDTO cityCreatedDTO) {
        return cityRepository.existsByName(cityCreatedDTO.getName()).
                flatMap(city -> {if(city)  {return Mono.error(RestException.badRequest("City already exists"));}
                else return cityRepository.save(cityCreatedDTO.toEntity()).
                            thenReturn(ApiResult.successResponse(CityDTO.fromEntity(cityCreatedDTO.toEntity())));});
                      

    }

    @Override
    public Flux<ApiResult<List<CityWeatherDto>>> getCitiesWithDisabled() {
        return cityRepository.findAll()
                .map(city -> ApiResult.successResponse(Collections.singletonList(CityWeatherDto.fromEntity(city))))
                        .defaultIfEmpty(ApiResult.successResponse(Collections.emptyList()));
    }

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
