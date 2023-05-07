package com.figo.weatherapp.service.auth;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface AuthService {


    Mono<ApiResult<TokenDTO>> signUpApplication(SignUpDTO signUpDTO);


    Mono<ApiResult<TokenDTO>> signInApplication(SignInDTO signInDTO);







}
