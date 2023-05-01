package com.figo.weatherapp.repository;

import com.figo.weatherapp.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long>{
}
