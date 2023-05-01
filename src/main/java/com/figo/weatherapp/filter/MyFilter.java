package com.figo.weatherapp.filter;


import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.net.ErrorData;
import com.figo.weatherapp.utils.AppConstant;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class MyFilter implements Filter {

    private final Environment environment;
    private final Gson gson;

    @Value("${spring.profiles.active}")
    private String activeProfiles;


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        writeLog(servletRequest);
        if (!"test".equalsIgnoreCase(activeProfiles)) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String serviceUsername = httpServletRequest.getHeader(AppConstant.SERVICE_USERNAME_HEADER);
            String servicePassword = httpServletRequest.getHeader(AppConstant.SERVICE_PASSWORD_HEADER);

            if ((!(httpServletRequest.getRequestURI().contains("actuator/refresh") || httpServletRequest.getRequestURI().contains("swagger") || httpServletRequest.getRequestURI().contains("api-docs")) &&
                    (serviceUsername == null || servicePassword == null ||
                            environment.getProperty(serviceUsername) == null ||
                            !Objects.equals(environment.getProperty(serviceUsername), servicePassword)))) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                ApiResult<ErrorData> errorDataApiResult = ApiResult.errorResponse("ACCESS_IS_NOT_POSSIBLE", 403);
                httpServletResponse.getWriter().write(gson.toJson(errorDataApiResult));
                httpServletResponse.setStatus(403);
                httpServletResponse.setContentType("application/json");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private void writeLog(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getRequestURI().startsWith("/api/education")) {
            log.info("Request - {}:{}", request.getMethod().toUpperCase(), request.getRequestURI());
        }
    }
}
