package com.figo.weatherapp.feign;


import com.figo.weatherapp.payload.CityDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;


@ReactiveFeignClient(
        name = "weather-service",
        url = "https://api.weatherapi.com/v1/current.json?key=efea79f1a4484d4d89d70701230505&q={city}",
        configuration = CustomerClientConfig.class
)
public interface FeignService {

     @GetMapping
     Mono<String> getWeather(@PathVariable String city);
}
