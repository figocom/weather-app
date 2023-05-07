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



    /**
     * The signUpApplication function is used to sign up a new application.
     *
     *
     * @param signUpDTO signUpDTO Get the user's email and password
     *
     * @return A mono&lt;apiresult&lt;tokendto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<TokenDTO>> signUpApplication(SignUpDTO signUpDTO) {

        return authService.signUpApplication(signUpDTO);
    }

    /**
     * The signInApplication function is used to sign in an application.
     *
     * @param signInDTO signInDTO Get the username and password from the request body
     *
     * @return A mono&lt;apiresult&lt;tokendto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<TokenDTO>> signInApplication(SignInDTO signInDTO) {

        return authService.signInApplication(signInDTO);
    }


}
