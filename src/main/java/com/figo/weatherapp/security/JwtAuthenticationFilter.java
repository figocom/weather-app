package com.figo.weatherapp.security;

import com.figo.weatherapp.entity.AuthUser;
import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.net.ErrorData;
import com.figo.weatherapp.repository.AuthUserRepository;
import com.figo.weatherapp.utils.AppConstant;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j

@Component
public class JwtAuthenticationFilter
        extends OncePerRequestFilter {

    private final Gson gson;
    private final Environment environment;
    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(Gson gson, Environment environment, @Lazy AuthUserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.gson = gson;
        this.environment = environment;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest httpServletRequest,
                                    @NotNull HttpServletResponse httpServletResponse,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        log.info("httpServletRequest {}", httpServletRequest);

        if ((!(httpServletRequest.getRequestURI().contains("actuator/refresh") || httpServletRequest.getRequestURI().contains("swagger") || httpServletRequest.getRequestURI().contains("api-docs"))))  {
            ApiResult<ErrorData> errorDataApiResult = ApiResult.errorResponse("FORBIDDEN", 403);
            httpServletResponse.getWriter().write(gson.toJson(errorDataApiResult));
            httpServletResponse.setStatus(403);
            httpServletResponse.setContentType("application/json");
            return;
        }
        try {
            log.info("httpServletRequest {}", httpServletRequest);
            //TOKEN YOKI BASIC NI OLIB SISTEMAGA KIRITADI
            setUserPrincipalIfAllOk(httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void setUserPrincipalIfAllOk(HttpServletRequest request) {
        String authorization = request.getHeader(AppConstant.AUTHORIZATION_HEADER);

        log.info("authorization {}", authorization);

        if (authorization != null) {
            AuthUser user = null;
            if (authorization.startsWith("Bearer ")) {
                user = getUserFromBearerToken(authorization);
            } else if (authorization.startsWith("Basic ")) {
                user = getUserFromBasicToken(authorization);
            }
            if (user != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }

    public AuthUser getUserFromBasicToken(String token) {
        log.info("token {}", token);
        token = token.substring("Basic".length()).trim();
        log.info("token {}", token);
        byte[] decode = Base64.getDecoder().decode(token);
        token = new String(decode, Charset.defaultCharset());
        String[] split = token.split(":", 2);
        log.info("token {}", token);
        log.info("split[0] {}", split[0]);
        //USERNI PHONENUMBER ORQALI DB DAN OLYAPDI TOPILMASA BO'SH OPTIONAL QAYTARADI
        Optional<AuthUser> optionalUser = userRepository.findFirstByPhoneNumberAndEnabledIsTrueAndAccountNonExpiredIsTrueAndCredentialsNonExpiredIsTrueAndAccountNonLockedIsTrue(split[0]);
        if (optionalUser.isPresent()) {
            AuthUser user = optionalUser.get();
            log.info("user {}", user);
            if (passwordEncoder.matches(split[1], user.getPassword()))
                return optionalUser.get();
        }
        return null;
    }

    public AuthUser getUserFromBearerToken(String token) {
        try {
//            log.info("Redis  begin {}",token);
            token = token.substring("Bearer".length()).trim();
            if (jwtTokenProvider.validToken(token, true)) {
                String userId = jwtTokenProvider.getUserIdFromToken(token, true);
//                log.info("Redis end {}",userId);
                return userRepository.findById(UUID.fromString(userId)).orElse(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
