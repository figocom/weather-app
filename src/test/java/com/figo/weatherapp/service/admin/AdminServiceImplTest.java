package com.figo.weatherapp.service.admin;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.figo.weatherapp.entity.AuthUser;
import com.figo.weatherapp.payload.UserEditByAdminDTO;
import com.figo.weatherapp.repository.AuthUserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ReplayProcessor;

@ContextConfiguration(classes = {AdminServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AdminServiceImplTest {
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @MockBean
    private AuthUserRepository authUserRepository;

    /**
     * Method under test: {@link AdminServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers() {
        when(authUserRepository.findAll()).thenReturn(DirectProcessor.create());
        adminServiceImpl.getUsers();
        verify(authUserRepository).findAll();
    }


    /**
     * Method under test: {@link AdminServiceImpl#getUsers()}
     */
    @Test
    void testGetUsers3() {
        when(authUserRepository.findAll()).thenReturn(ReplayProcessor.create(3, true));
        adminServiceImpl.getUsers();
        verify(authUserRepository).findAll();
    }

    /**
     * Method under test: {@link AdminServiceImpl#getUserDetails(String)}
     */
    @Test
    void testGetUserDetails2() {
        when(authUserRepository.findById(Mockito.<Integer>any())).thenReturn(mock(Mono.class));
        adminServiceImpl.getUserDetails("42");
        verify(authUserRepository).findById(Mockito.<Integer>any());
    }
    /**
     * Method under test: {@link AdminServiceImpl#editUser(UserEditByAdminDTO)}
     */
    @Test
    void testEditUser4() {
        when(authUserRepository.findById(Mockito.<Integer>any())).thenReturn(mock(Mono.class));
        adminServiceImpl.editUser(new UserEditByAdminDTO("42", true, "Role", true, true, true));
        verify(authUserRepository).findById(Mockito.<Integer>any());
    }
}

