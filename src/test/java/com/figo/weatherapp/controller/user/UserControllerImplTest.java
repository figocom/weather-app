package com.figo.weatherapp.controller.user;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.CityWeatherDto;
import com.figo.weatherapp.payload.ResetPasswordDTO;
import com.figo.weatherapp.payload.UserEditDTO;
import com.figo.weatherapp.service.user.UserService;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.mock.http.server.reactive.MockServerHttpResponse;
import org.springframework.security.web.server.context.SecurityContextServerWebExchange;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import org.springframework.web.server.adapter.DefaultServerWebExchange;
import org.springframework.web.server.i18n.AcceptHeaderLocaleContextResolver;
import org.springframework.web.server.session.WebSessionManager;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Mono;

@ContextConfiguration(classes = {UserControllerImpl.class})
@ExtendWith(SpringExtension.class)
class UserControllerImplTest {
    @Autowired
    private UserControllerImpl userControllerImpl;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserControllerImpl#getApplicationUserMe(ServerWebExchange)}
     */


    /**
     * Method under test: {@link UserControllerImpl#getApplicationUserMe(ServerWebExchange)}
     */
    @Test
    void testGetApplicationUserMe2() {
        when(userService.getApplicationUserMe(Mockito.<ServerWebExchange>any())).thenReturn(null);
        ServerHttpRequestDecorator request = mock(ServerHttpRequestDecorator.class);
        when(request.getHeaders()).thenReturn(new HttpHeaders());
        when(request.getId()).thenReturn("https://example.org/example");
        WebSessionManager sessionManager = mock(WebSessionManager.class);
        when(sessionManager.getSession(Mockito.<ServerWebExchange>any())).thenReturn(mock(Mono.class));
        MockServerHttpResponse response = new MockServerHttpResponse();
        DefaultServerCodecConfigurer codecConfigurer = new DefaultServerCodecConfigurer();
        assertNull(
                userControllerImpl
                        .getApplicationUserMe(new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(
                                        new SecurityContextServerWebExchange(new DefaultServerWebExchange(request, response,
                                                sessionManager, codecConfigurer, new AcceptHeaderLocaleContextResolver()), null),
                                        null),
                                null), null)));
        verify(userService).getApplicationUserMe(Mockito.<ServerWebExchange>any());
        verify(request).getId();
        verify(request, atLeast(1)).getHeaders();
        verify(sessionManager).getSession(Mockito.<ServerWebExchange>any());
    }



    /**
     * Method under test: {@link UserControllerImpl#editUserSelf(UserEditDTO, ServerWebExchange)}
     */
    @Test
    void testEditUserSelf2() {
        when(userService.editUserSelf(Mockito.<UserEditDTO>any(), Mockito.<ServerWebExchange>any())).thenReturn(null);
        UserEditDTO userEditDTO = mock(UserEditDTO.class);
        doNothing().when(userEditDTO).setCurrentPassword(Mockito.<String>any());
        doNothing().when(userEditDTO).setFirstName(Mockito.<String>any());
        doNothing().when(userEditDTO).setId(Mockito.<Integer>any());
        doNothing().when(userEditDTO).setLastName(Mockito.<String>any());
        userEditDTO.setCurrentPassword("iloveyou");
        userEditDTO.setFirstName("Jane");
        userEditDTO.setId(1);
        userEditDTO.setLastName("Doe");
        assertNull(userControllerImpl.editUserSelf(userEditDTO,
                new SecurityContextServerWebExchange(
                        new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(mock(DefaultServerWebExchange.class), null), null), null),
                        null)));
        verify(userService).editUserSelf(Mockito.<UserEditDTO>any(), Mockito.<ServerWebExchange>any());
        verify(userEditDTO).setCurrentPassword(Mockito.<String>any());
        verify(userEditDTO).setFirstName(Mockito.<String>any());
        verify(userEditDTO).setId(Mockito.<Integer>any());
        verify(userEditDTO).setLastName(Mockito.<String>any());
    }



    /**
     * Method under test: {@link UserControllerImpl#resetPassword(ServerWebExchange, ResetPasswordDTO)}
     */
    @Test
    void testResetPassword2() {
        when(userService.resetPassword(Mockito.<ResetPasswordDTO>any(), Mockito.<ServerWebExchange>any()))
                .thenReturn(null);
        ServerHttpRequestDecorator request = mock(ServerHttpRequestDecorator.class);
        when(request.getHeaders()).thenReturn(new HttpHeaders());
        when(request.getId()).thenReturn("https://example.org/example");
        WebSessionManager sessionManager = mock(WebSessionManager.class);
        when(sessionManager.getSession(Mockito.<ServerWebExchange>any())).thenReturn(mock(Mono.class));
        MockServerHttpResponse response = new MockServerHttpResponse();
        DefaultServerCodecConfigurer codecConfigurer = new DefaultServerCodecConfigurer();
        SecurityContextServerWebExchange exchange = new SecurityContextServerWebExchange(
                new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                        new SecurityContextServerWebExchange(new DefaultServerWebExchange(request, response, sessionManager,
                                codecConfigurer, new AcceptHeaderLocaleContextResolver()), null),
                        null), null),
                null);

        assertNull(userControllerImpl.resetPassword(exchange, new ResetPasswordDTO("iloveyou", "iloveyou", "iloveyou")));
        verify(userService).resetPassword(Mockito.<ResetPasswordDTO>any(), Mockito.<ServerWebExchange>any());
        verify(request).getId();
        verify(request, atLeast(1)).getHeaders();
        verify(sessionManager).getSession(Mockito.<ServerWebExchange>any());
    }




    /**
     * Method under test: {@link UserControllerImpl#subscribeCityWeather(String, ServerWebExchange)}
     */
    @Test
    void testSubscribeCityWeather2() {
        when(userService.subscribeCityWeather(Mockito.<String>any(), Mockito.<ServerWebExchange>any())).thenReturn(null);
        ServerHttpRequestDecorator request = mock(ServerHttpRequestDecorator.class);
        when(request.getHeaders()).thenReturn(new HttpHeaders());
        when(request.getId()).thenReturn("https://example.org/example");
        WebSessionManager sessionManager = mock(WebSessionManager.class);
        when(sessionManager.getSession(Mockito.<ServerWebExchange>any())).thenReturn(mock(Mono.class));
        MockServerHttpResponse response = new MockServerHttpResponse();
        DefaultServerCodecConfigurer codecConfigurer = new DefaultServerCodecConfigurer();
        assertNull(
                userControllerImpl.subscribeCityWeather("42",
                        new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(
                                        new SecurityContextServerWebExchange(new DefaultServerWebExchange(request, response,
                                                sessionManager, codecConfigurer, new AcceptHeaderLocaleContextResolver()), null),
                                        null),
                                null), null)));
        verify(userService).subscribeCityWeather(Mockito.<String>any(), Mockito.<ServerWebExchange>any());
        verify(request).getId();
        verify(request, atLeast(1)).getHeaders();
        verify(sessionManager).getSession(Mockito.<ServerWebExchange>any());
    }

    /**
     * Method under test: {@link UserControllerImpl#getSubscribedCitiesWeather(ServerWebExchange)}
     */


    /**
     * Method under test: {@link UserControllerImpl#getSubscribedCitiesWeather(ServerWebExchange)}
     */
    @Test
    void testGetSubscribedCitiesWeather2() {
        DirectProcessor<ApiResult<List<CityWeatherDto>>> createResult = DirectProcessor.create();
        when(userService.getSubscribedCitiesWeather(Mockito.<ServerWebExchange>any())).thenReturn(createResult);
        ServerHttpRequestDecorator request = mock(ServerHttpRequestDecorator.class);
        when(request.getHeaders()).thenReturn(new HttpHeaders());
        when(request.getId()).thenReturn("https://example.org/example");
        WebSessionManager sessionManager = mock(WebSessionManager.class);
        when(sessionManager.getSession(Mockito.<ServerWebExchange>any())).thenReturn(mock(Mono.class));
        MockServerHttpResponse response = new MockServerHttpResponse();
        DefaultServerCodecConfigurer codecConfigurer = new DefaultServerCodecConfigurer();
        assertSame(createResult,
                userControllerImpl
                        .getSubscribedCitiesWeather(new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(
                                        new SecurityContextServerWebExchange(new DefaultServerWebExchange(request, response,
                                                sessionManager, codecConfigurer, new AcceptHeaderLocaleContextResolver()), null),
                                        null),
                                null), null)));
        verify(userService).getSubscribedCitiesWeather(Mockito.<ServerWebExchange>any());
        verify(request).getId();
        verify(request, atLeast(1)).getHeaders();
        verify(sessionManager).getSession(Mockito.<ServerWebExchange>any());
    }


    /**
     * Method under test: {@link UserControllerImpl#getAllCities(ServerWebExchange)}
     */
    @Test
    void testGetAllCities2() {
        DirectProcessor<ApiResult<List<CityWeatherDto>>> createResult = DirectProcessor.create();
        when(userService.getAllCities()).thenReturn(createResult);
        ServerHttpRequestDecorator request = mock(ServerHttpRequestDecorator.class);
        when(request.getHeaders()).thenReturn(new HttpHeaders());
        when(request.getId()).thenReturn("https://example.org/example");
        WebSessionManager sessionManager = mock(WebSessionManager.class);
        when(sessionManager.getSession(Mockito.<ServerWebExchange>any())).thenReturn(mock(Mono.class));
        MockServerHttpResponse response = new MockServerHttpResponse();
        DefaultServerCodecConfigurer codecConfigurer = new DefaultServerCodecConfigurer();
        assertSame(createResult,
                userControllerImpl
                        .getAllCities(new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(
                                        new SecurityContextServerWebExchange(new DefaultServerWebExchange(request, response,
                                                sessionManager, codecConfigurer, new AcceptHeaderLocaleContextResolver()), null),
                                        null),
                                null), null)));
        verify(userService).getAllCities();
        verify(request).getId();
        verify(request, atLeast(1)).getHeaders();
        verify(sessionManager).getSession(Mockito.<ServerWebExchange>any());
    }


    /**
     * Method under test: {@link UserControllerImpl#removeSubscription(Integer, ServerWebExchange)}
     */
    @Test
    void testRemoveSubscription2() {
        when(userService.removeSubscription(Mockito.<Integer>any(), Mockito.<ServerWebExchange>any())).thenReturn(null);
        ServerHttpRequestDecorator request = mock(ServerHttpRequestDecorator.class);
        when(request.getHeaders()).thenReturn(new HttpHeaders());
        when(request.getId()).thenReturn("https://example.org/example");
        WebSessionManager sessionManager = mock(WebSessionManager.class);
        when(sessionManager.getSession(Mockito.<ServerWebExchange>any())).thenReturn(mock(Mono.class));
        MockServerHttpResponse response = new MockServerHttpResponse();
        DefaultServerCodecConfigurer codecConfigurer = new DefaultServerCodecConfigurer();
        assertNull(
                userControllerImpl.removeSubscription(1,
                        new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(
                                        new SecurityContextServerWebExchange(new DefaultServerWebExchange(request, response,
                                                sessionManager, codecConfigurer, new AcceptHeaderLocaleContextResolver()), null),
                                        null),
                                null), null)));
        verify(userService).removeSubscription(Mockito.<Integer>any(), Mockito.<ServerWebExchange>any());
        verify(request).getId();
        verify(request, atLeast(1)).getHeaders();
        verify(sessionManager).getSession(Mockito.<ServerWebExchange>any());
    }
}

