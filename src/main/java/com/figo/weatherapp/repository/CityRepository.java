package com.figo.weatherapp.repository;

import com.figo.weatherapp.entity.City;
import org.reactivestreams.Publisher;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CityRepository extends ReactiveCrudRepository<City, Integer> {
    Flux<City> findAllByEnabledTrue();

    Mono<Boolean> existsByName(String name);

}
