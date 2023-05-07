package com.figo.weatherapp.controller.auth;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.figo.weatherapp.payload.SignInDTO;
import com.figo.weatherapp.payload.SignUpDTO;
import com.figo.weatherapp.service.auth.AuthService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthControllerImpl.class})
@ExtendWith(SpringExtension.class)
class AuthControllerImplTest {
    @Autowired
    private AuthControllerImpl authControllerImpl;

    @MockBean
    private AuthService authService;

    /**
     * Method under test: {@link AuthControllerImpl#signUpApplication(SignUpDTO)}
     */
    @Test
    void testSignUpApplication() {
        when(authService.signUpApplication(Mockito.<SignUpDTO>any())).thenReturn(null);
        assertNull(authControllerImpl.signUpApplication(new SignUpDTO("Jane", "Doe", "janedoe", "iloveyou", "iloveyou")));
        verify(authService).signUpApplication(Mockito.<SignUpDTO>any());
    }

    /**
     * Method under test: {@link AuthControllerImpl#signInApplication(SignInDTO)}
     */
    @Test
    void testSignInApplication() {
        when(authService.signInApplication(Mockito.<SignInDTO>any())).thenReturn(null);
        assertNull(authControllerImpl.signInApplication(new SignInDTO("janedoe", "iloveyou")));
        verify(authService).signInApplication(Mockito.<SignInDTO>any());
    }
}

