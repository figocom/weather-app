package com.figo.weatherapp.controller.auth;


import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import com.figo.weatherapp.utils.AppConstant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequestMapping(value = AuthController.AUTH_CONTROLLER_PATH)
public interface AuthController {

    String AUTH_CONTROLLER_PATH = AppConstant.BASE_PATH_V1 + "auth/";
    String APPLICATION_SIGN_UP_PATH = "application/sign-up";
    String APPLICATION_SIGN_IN_PATH = "application/sign-in";
   // String FORGOT_APPLICATION_PASSWORD_PATH = "application/forgot-password";






    @PostMapping(APPLICATION_SIGN_UP_PATH)
    Mono<ApiResult<TokenDTO>> signUpApplication(@Valid @RequestBody SignUpDTO signUpDTO);

    /**
     * App UCHUN SIGN IN
     */
    @PostMapping(APPLICATION_SIGN_IN_PATH)
    Mono<ApiResult<TokenDTO>> signInApplication(@Valid @RequestBody SignInDTO signInDTO);







}
