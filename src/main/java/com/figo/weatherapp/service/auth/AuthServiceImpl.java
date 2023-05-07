package com.figo.weatherapp.service.auth;

import com.figo.weatherapp.entity.AuthUser;
import com.figo.weatherapp.enums.Role;
import com.figo.weatherapp.exception.RestException;
import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.SignInDTO;
import com.figo.weatherapp.payload.SignUpDTO;
import com.figo.weatherapp.payload.TokenDTO;
import com.figo.weatherapp.repository.AuthUserRepository;
import com.figo.weatherapp.security.JwtTokenProvider;
import com.figo.weatherapp.service.auth.AuthService;
import com.figo.weatherapp.utils.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service

public class AuthServiceImpl implements ReactiveUserDetailsService, AuthService {

    private final AuthUserRepository authUserRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public AuthServiceImpl(AuthUserRepository authUserRepository, JwtTokenProvider jwtTokenProvider) {
        this.authUserRepository = authUserRepository;
        this.jwtTokenProvider = jwtTokenProvider;

    }



    @Override
    public Mono<ApiResult<TokenDTO>> signUpApplication(SignUpDTO signUpDTO) {
        if (!signUpDTO.getPassword().equals(signUpDTO.getPrePassword())){
            return Mono.error(RestException.badRequest("password not match"));
        }
        AuthUser authUser = new AuthUser();
        authUser.setUsername(signUpDTO.getUsername());
        authUser.setPassword(signUpDTO.getPassword());
        authUser.setFirstName(signUpDTO.getFirstName());
        authUser.setLastName(signUpDTO.getLastName());
        authUser.setRole(Role.USER.name());
        String accessToken = jwtTokenProvider.createToken(authUser);
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setAccessToken(accessToken);
        tokenDTO.setTokenType(AppConstant.AUTHORIZATION_HEADER_PREFIX);
        return authUserRepository.
                findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(signUpDTO.getUsername()).
                flatMap(authUser1 -> Mono.error(RestException.badRequest("user already exist"))).
                and(authUserRepository.save(authUser)).thenReturn(ApiResult.successResponse(tokenDTO));

    }

    @Override
    public Mono<ApiResult<TokenDTO>> signInApplication(SignInDTO signInDTO) {
     return authUserRepository.
             findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(signInDTO.getUsername()).
             switchIfEmpty(Mono.error(RestException.notFound(signInDTO.getUsername()))).
             flatMap(authUser1 -> Mono.just(ApiResult.successResponse(new TokenDTO(AppConstant.AUTHORIZATION_HEADER_PREFIX,jwtTokenProvider.createToken(authUser1)))));
    }



    @Override
    public Mono<UserDetails> findByUsername(String username) {
       return authUserRepository.findFirstByUsernameAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(username).cast(UserDetails.class);
    }
}