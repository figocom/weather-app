package com.figo.weatherapp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Locale;

/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
@Configuration
public class CustomMessageSourceConfig {

    /**
     * The messageSource function creates a ReloadableResourceBundleMessageSource object, which is the implementation of MessageSource interface.
     * The message source will be used to resolve messages in the application.
     * It loads messages from property files and supports reloading them at runtime, making it easy to develop applications without restarting the server.

     *
     * @return A messagesource object
     *
     * @docauthor Manguberdi
     */
    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setDefaultLocale(new Locale("uz"));
        return messageSource;
    }

    /**
     * The localValidatorFactoryBean function creates a LocalValidatorFactoryBean object, which is used to create
     * a Validator object. The Validator interface defines methods for validating objects and their properties.
     *
     * The localValidatorFactoryBean function sets the validationMessageSource property of the factory bean to be equal
     * to the messageSource function, which returns an instance of MessageSourceResolvable (a class that implements
     * MessageSource). This allows us to use our custom messages in our error messages when we validate form data.


     *
     *
     * @return A localvalidatorfactorybean object
     *
     * @docauthor Manguberdi
     */
    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean() {

        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setValidationMessageSource(messageSource());
        return factoryBean;
    }
}
