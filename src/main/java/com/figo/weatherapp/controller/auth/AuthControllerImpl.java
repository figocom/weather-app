package com.figo.weatherapp.controller.auth;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import com.figo.weatherapp.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController{
    private  final AuthService authService;



    @Override
    public Mono<ApiResult<TokenDTO>> signUpApplication(SignUpDTO signUpDTO) {
        return authService.signUpApplication(signUpDTO);
    }

    @Override
    public Mono<ApiResult<TokenDTO>> signInApplication(SignInDTO signInDTO) {
        return authService.signInApplication(signInDTO);
    }


}
