package com.figo.weatherapp.feign;


import com.figo.weatherapp.exception.RestException;
import com.figo.weatherapp.net.ApiResult;
import com.google.gson.Gson;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomFeignErrorDecoder implements ErrorDecoder {

    private final Gson gson;



    @Override
    public Exception decode(final String methodKey, final Response response) {
        final String error = getResponseBodyAsString(response.body());
        log.error("Exception in another server with error code: {}, URL: {}, Body: {}", response.status(), response.request().url(), error);
        try {
            ApiResult<?> apiResult = gson.fromJson(error, ApiResult.class);
            System.out.println(apiResult);
            return RestException.restThrow(apiResult.getErrors(), HttpStatus.valueOf(response.status()));

        } catch (Exception e) {

            return RestException.restThrow(
//                    "Error in another server",
                    error,
                    HttpStatus.BAD_REQUEST);
        }

    }

    private String getResponseBodyAsString(final Response.Body body) {
        try {
            byte[] bytes = StreamUtils.copyToByteArray(body.asInputStream());
            return new String(bytes);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
