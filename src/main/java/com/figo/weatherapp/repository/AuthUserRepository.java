package com.figo.weatherapp.repository;

import com.figo.weatherapp.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{
    //USERNI PHONENUMBER ORQALI DB DAN OLYAPDI TOPILMASA BO'SH QAYTARADI
    Optional<AuthUser> findFirstByPhoneNumberAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(String phoneNumber);

    Optional<AuthUser> findFirstByPhoneNumberAndCanEnterWithBasicAuthIsTrueAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(String phoneNumber);

    Optional<AuthUser> findByEmailAndEnabledIsTrue(String email);

    boolean existsByPhoneNumberAndEnabledIsTrue(String phoneNumber);

    boolean existsByIdNotAndPhoneNumberAndEnabledIsTrue(UUID id, String phoneNumber);
    Optional<AuthUser> findByPhoneNumberAndEnabledIsTrue(String phoneNumber);

   Optional<AuthUser> findById(UUID uuid);
}
