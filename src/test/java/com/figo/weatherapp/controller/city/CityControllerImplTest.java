package com.figo.weatherapp.controller.city;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.CityCreatedDTO;
import com.figo.weatherapp.payload.CityDTO;
import com.figo.weatherapp.payload.CityWeatherDto;
import com.figo.weatherapp.service.city.CityService;

import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.server.context.SecurityContextServerWebExchange;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.adapter.DefaultServerWebExchange;
import reactor.core.publisher.DirectProcessor;

@ContextConfiguration(classes = {CityControllerImpl.class})
@ExtendWith(SpringExtension.class)
class CityControllerImplTest {
    @Autowired
    private CityControllerImpl cityControllerImpl;

    @MockBean
    private CityService cityService;

    /**
     * Method under test: {@link CityControllerImpl#getCities()}
     */
    @Test
    void testGetCities() {
        DirectProcessor<ApiResult<List<CityWeatherDto>>> createResult = DirectProcessor.create();
        when(cityService.getCities()).thenReturn(createResult);
        assertSame(createResult, cityControllerImpl.getCities());
        verify(cityService).getCities();
    }

    /**
     * Method under test: {@link CityControllerImpl#getCitiesWithDisabled()}
     */
    @Test
    void testGetCitiesWithDisabled() {
        DirectProcessor<ApiResult<List<CityWeatherDto>>> createResult = DirectProcessor.create();
        when(cityService.getCitiesWithDisabled()).thenReturn(createResult);
        assertSame(createResult, cityControllerImpl.getCitiesWithDisabled());
        verify(cityService).getCitiesWithDisabled();
    }

    /**
     * Method under test: {@link CityControllerImpl#getCityById(String)}
     */
    @Test
    void testGetCityById() {
        when(cityService.getCityById(Mockito.<String>any())).thenReturn(null);
        assertNull(cityControllerImpl.getCityById("42"));
        verify(cityService).getCityById(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CityControllerImpl#updateCityWeather(String)}
     */
    @Test
    void testUpdateCityWeather() {
        when(cityService.updateCityWeather(Mockito.<String>any())).thenReturn(null);
        assertNull(cityControllerImpl.updateCityWeather("42"));
        verify(cityService).updateCityWeather(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CityControllerImpl#updateCityWeatherAll()}
     */
    @Test
    void testUpdateCityWeatherAll() {
        when(cityService.updateCityWeatherAll()).thenReturn(null);
        assertNull(cityControllerImpl.updateCityWeatherAll());
        verify(cityService).updateCityWeatherAll();
    }

    /**
     * Method under test: {@link CityControllerImpl#updateCity(String, CityCreatedDTO, ServerWebExchange)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCity() {


        CityCreatedDTO cityCreatedDTO = new CityCreatedDTO();
        cityControllerImpl.updateCity("42", cityCreatedDTO,
                new SecurityContextServerWebExchange(
                        new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(null, null), null), null),
                        null));
    }

    /**
     * Method under test: {@link CityControllerImpl#updateCity(String, CityCreatedDTO, ServerWebExchange)}
     */
    @Test
    void testUpdateCity2() {
        when(cityService.updateCity(Mockito.<String>any(), Mockito.<CityCreatedDTO>any(),
                Mockito.<ServerWebExchange>any())).thenReturn(null);
        CityCreatedDTO cityCreatedDTO = mock(CityCreatedDTO.class);
        assertNull(cityControllerImpl.updateCity("42", cityCreatedDTO,
                new SecurityContextServerWebExchange(
                        new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(mock(DefaultServerWebExchange.class), null), null), null),
                        null)));
        verify(cityService).updateCity(Mockito.<String>any(), Mockito.<CityCreatedDTO>any(),
                Mockito.<ServerWebExchange>any());
    }

    /**
     * Method under test: {@link CityControllerImpl#createCity(CityCreatedDTO)}
     */
    @Test
    void testCreateCity() {
        when(cityService.createCity(Mockito.<CityCreatedDTO>any())).thenReturn(null);
        assertNull(cityControllerImpl.createCity(new CityCreatedDTO()));
        verify(cityService).createCity(Mockito.<CityCreatedDTO>any());
    }

    /**
     * Method under test: {@link CityControllerImpl#updateCityWeatherManual(String, CityDTO)}
     */
    @Test
    void testUpdateCityWeatherManual() {
        when(cityService.updateCityWeatherManual(Mockito.<String>any(), Mockito.<CityDTO>any())).thenReturn(null);
        assertNull(cityControllerImpl.updateCityWeatherManual("42", new CityDTO()));
        verify(cityService).updateCityWeatherManual(Mockito.<String>any(), Mockito.<CityDTO>any());
    }
}

