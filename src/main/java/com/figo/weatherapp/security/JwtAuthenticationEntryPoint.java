package com.figo.weatherapp.security;


import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.net.ErrorData;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final Gson gson;

    public JwtAuthenticationEntryPoint(Gson gson) {
        this.gson = gson;
    }

    //YOPIQ YOLGA TOKENSIZ KELGANIDA BU METHODGA TUSHADI VA 403 QAYTARADI
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.error("Responding with unauthorized error. URL -  {}, Message - {}", httpServletRequest.getRequestURI(), e.getMessage());
        ApiResult<ErrorData> errorDataApiResult = ApiResult.errorResponse("Forbidden", 403);
        httpServletResponse.getWriter().write(gson.toJson(errorDataApiResult));
        httpServletResponse.setStatus(403);
        httpServletResponse.setContentType("application/json");
    }

}
