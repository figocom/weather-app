package com.figo.weatherapp.repository;

import com.figo.weatherapp.entity.AuthUser;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends ReactiveCrudRepository<AuthUser, Integer> {
    Mono<AuthUser> findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(String phoneNumber);

    Mono<AuthUser> findFirstByIdAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(Integer id);

    Optional<AuthUser> findByUsername(String username);
}
