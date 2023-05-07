package com.figo.weatherapp.repository;

import com.figo.weatherapp.entity.AuthUser;
import com.figo.weatherapp.entity.City;
import com.figo.weatherapp.entity.Subscription;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SubscriptionRepository extends ReactiveCrudRepository<Subscription , Integer> {


    Flux<Subscription> findAllByUserId(Integer id);

    Flux<Subscription> findAllByUserIdAndActiveIsTrue(Integer id);

    Mono<? extends Subscription> findFirstByUserIdAndCityId(Integer id, Integer cityId);

}
