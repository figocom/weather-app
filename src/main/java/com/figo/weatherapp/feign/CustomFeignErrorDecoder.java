package com.figo.weatherapp.feign;


import com.figo.weatherapp.exception.RestException;
import com.figo.weatherapp.net.ApiResult;
import com.google.gson.Gson;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;

import java.io.IOException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomFeignErrorDecoder implements ErrorDecoder {

    private final Gson gson;



    /**
     * The decode function is responsible for translating the response from the server into an exception.
     * The default implementation will create a new RuntimeException with the message from the response body,
     * but you can override this method to create your own custom exceptions.

     *
     * @param methodKey String methodKey Identify the method that is being called
     * @param response Response Get the response body as a string
     *
     * @return An exception, which is then thrown by the feign client
     *
     * @docauthor Manguberdi
     */
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

    /**
     * The getResponseBodyAsString function takes a Response.Body object and returns the body as a String.
     *
     *
     * @param body Response.Body Get the response body
     *
     * @return The response body as a string
     *
     * @docauthor Manguberdi
     */
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
