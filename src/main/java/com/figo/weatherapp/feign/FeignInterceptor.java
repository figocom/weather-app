package com.figo.weatherapp.feign;

import com.figo.weatherapp.utils.AppConstant;
import com.figo.weatherapp.utils.CommonUtils;
import com.nimbusds.common.contenttype.ContentType;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@RequiredArgsConstructor
public class FeignInterceptor implements RequestInterceptor {

    @Value("${service.serviceUsername}")
    private String usernameService;

    private final Environment environment;

    @Override
    public void apply(RequestTemplate template) {
        template.header(AppConstant.AUTHORIZATION_HEADER, CommonUtils.getTokenFromRequest());
        template.header(AppConstant.SERVICE_USERNAME_HEADER, usernameService);
        template.header(AppConstant.SERVICE_PASSWORD_HEADER, environment.getProperty(usernameService));
        template.header("Accept", ContentType.APPLICATION_JSON.getBaseType());
    }
}

