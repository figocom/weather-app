package com.figo.weatherapp.controller.admin;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.payload.UserEditByAdminDTO;
import com.figo.weatherapp.service.admin.AdminService;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.DirectProcessor;

@ContextConfiguration(classes = {AdminControllerImpl.class})
@ExtendWith(SpringExtension.class)
class AdminControllerImplTest {
    @Autowired
    private AdminControllerImpl adminControllerImpl;

    @MockBean
    private AdminService adminService;

    /**
     * Method under test: {@link AdminControllerImpl#getUsers()}
     */
    @Test
    void testGetUsers() {
        DirectProcessor<ApiResult<List<UserDTO>>> createResult = DirectProcessor.create();
        when(adminService.getUsers()).thenReturn(createResult);
        assertSame(createResult, adminControllerImpl.getUsers());
        verify(adminService).getUsers();
    }

    /**
     * Method under test: {@link AdminControllerImpl#getUserDetails(String)}
     */
    @Test
    void testGetUserDetails() {
        when(adminService.getUserDetails(Mockito.<String>any())).thenReturn(null);
        assertNull(adminControllerImpl.getUserDetails("42"));
        verify(adminService).getUserDetails(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AdminControllerImpl#editUser(UserEditByAdminDTO)}
     */
    @Test
    void testEditUser() {
        when(adminService.editUser(Mockito.<UserEditByAdminDTO>any())).thenReturn(null);
        assertNull(adminControllerImpl.editUser(new UserEditByAdminDTO("42", true, "Role", true, true, true)));
        verify(adminService).editUser(Mockito.<UserEditByAdminDTO>any());
    }
}

