package com.figo.weatherapp.service.city;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.figo.weatherapp.entity.City;
import com.figo.weatherapp.feign.FeignService;
import com.figo.weatherapp.payload.CityCreatedDTO;
import com.figo.weatherapp.repository.AuthUserRepository;
import com.figo.weatherapp.repository.CityRepository;
import com.figo.weatherapp.security.JwtTokenProvider;
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
import reactor.core.publisher.Mono;
import reactor.core.publisher.ReplayProcessor;

@ContextConfiguration(classes = {CityServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CityServiceImplTest {
    @MockBean
    private AuthUserRepository authUserRepository;

    @MockBean
    private CityRepository cityRepository;

    @Autowired
    private CityServiceImpl cityServiceImpl;

    @MockBean
    private FeignService feignService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private ObjectMapper objectMapper;

    /**
     * Method under test: {@link CityServiceImpl#getCities()}
     */
    @Test
    void testGetCities() {
        when(cityRepository.findAllByEnabledTrue()).thenReturn(DirectProcessor.create());
        cityServiceImpl.getCities();
        verify(cityRepository).findAllByEnabledTrue();
    }



    /**
     * Method under test: {@link CityServiceImpl#getCities()}
     */
    @Test
    void testGetCities3() {
        when(cityRepository.findAllByEnabledTrue()).thenReturn(ReplayProcessor.create(3, true));
        cityServiceImpl.getCities();
        verify(cityRepository).findAllByEnabledTrue();
    }

    /**
     * Method under test: {@link CityServiceImpl#getCities()}
     */
    @Test
    void testGetCities4() {
        when(cityRepository.findAllByEnabledTrue()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> cityServiceImpl.getCities());
        verify(cityRepository).findAllByEnabledTrue();
    }



    /**
     * Method under test: {@link CityServiceImpl#getCityById(String)}
     */
    @Test
    void testGetCityById2() {
        when(cityRepository.findById(Mockito.<Integer>any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> cityServiceImpl.getCityById("42"));
        verify(cityRepository).findById(Mockito.<Integer>any());
    }


    /**
     * Method under test: {@link CityServiceImpl#getCityById(String)}
     */
    @Test
    void testGetCityById4() {
        when(cityRepository.findById(Mockito.<Integer>any())).thenReturn(mock(Mono.class));
        cityServiceImpl.getCityById("42");
        verify(cityRepository).findById(Mockito.<Integer>any());
    }



    /**
     * Method under test: {@link CityServiceImpl#updateCityWeather(String)}
     */
    @Test
    void testUpdateCityWeather2() {
        when(cityRepository.findById(Mockito.<Integer>any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> cityServiceImpl.updateCityWeather("42"));
        verify(cityRepository).findById(Mockito.<Integer>any());
    }



    /**
     * Method under test: {@link CityServiceImpl#updateCityWeather(String)}
     */
    @Test
    void testUpdateCityWeather4() {
        when(cityRepository.findById(Mockito.<Integer>any())).thenReturn(mock(Mono.class));
        cityServiceImpl.updateCityWeather("42");
        verify(cityRepository).findById(Mockito.<Integer>any());
    }




    /**
     * Method under test: {@link CityServiceImpl#updateCity(String, CityCreatedDTO, ServerWebExchange)}
     */
    @Test
    void testUpdateCity3() {
        when(cityRepository.existsById(Mockito.<Integer>any())).thenThrow(new RuntimeException("An error occurred"));
        CityCreatedDTO cityCreatedDTO = mock(CityCreatedDTO.class);
        assertThrows(RuntimeException.class,
                () -> cityServiceImpl.updateCity("42", cityCreatedDTO, new SecurityContextServerWebExchange(
                        new SecurityContextServerWebExchange(new SecurityContextServerWebExchange(
                                new SecurityContextServerWebExchange(mock(DefaultServerWebExchange.class), null), null), null),
                        null)));
        verify(cityRepository).existsById(Mockito.<Integer>any());
    }








    /**
     * Method under test: {@link CityServiceImpl#createCity(CityCreatedDTO)}
     */
    @Test
    void testCreateCity4() {
        when(cityRepository.existsByName(Mockito.<String>any())).thenReturn(mock(Mono.class));
        CityCreatedDTO cityCreatedDTO = mock(CityCreatedDTO.class);
        when(cityCreatedDTO.getName()).thenReturn("Name");
        cityServiceImpl.createCity(cityCreatedDTO);
        verify(cityRepository).existsByName(Mockito.<String>any());
        verify(cityCreatedDTO).getName();
    }

    /**
     * Method under test: {@link CityServiceImpl#createCity(CityCreatedDTO)}
     */
    @Test
    void testCreateCity5() {
        when(cityRepository.existsByName(Mockito.<String>any())).thenReturn(mock(Mono.class));
        CityCreatedDTO cityCreatedDTO = mock(CityCreatedDTO.class);
        when(cityCreatedDTO.getName()).thenReturn("foo");
        cityServiceImpl.createCity(cityCreatedDTO);
        verify(cityRepository).existsByName(Mockito.<String>any());
        verify(cityCreatedDTO).getName();
    }

    /**
     * Method under test: {@link CityServiceImpl#createCity(CityCreatedDTO)}
     */
    @Test
    void testCreateCity6() {
        when(cityRepository.existsByName(Mockito.<String>any())).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> cityServiceImpl.createCity(new CityCreatedDTO()));
        verify(cityRepository).existsByName(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CityServiceImpl#getCitiesWithDisabled()}
     */
    @Test
    void testGetCitiesWithDisabled() {
        when(cityRepository.findAll()).thenReturn(DirectProcessor.create());
        cityServiceImpl.getCitiesWithDisabled();
        verify(cityRepository).findAll();
    }


    /**
     * Method under test: {@link CityServiceImpl#getCitiesWithDisabled()}
     */
    @Test
    void testGetCitiesWithDisabled3() {
        when(cityRepository.findAll()).thenReturn(ReplayProcessor.create(3, true));
        cityServiceImpl.getCitiesWithDisabled();
        verify(cityRepository).findAll();
    }

    /**
     * Method under test: {@link CityServiceImpl#getCitiesWithDisabled()}
     */
    @Test
    void testGetCitiesWithDisabled4() {
        when(cityRepository.findAll()).thenThrow(new RuntimeException("An error occurred"));
        assertThrows(RuntimeException.class, () -> cityServiceImpl.getCitiesWithDisabled());
        verify(cityRepository).findAll();
    }
}

