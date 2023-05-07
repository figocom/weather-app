package com.figo.weatherapp.controller.admin;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.payload.UserDetailDTO;
import com.figo.weatherapp.payload.UserEditByAdminDTO;
import com.figo.weatherapp.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
@RestController
@Slf4j
public class AdminControllerImpl implements AdminController{
    private final AdminService adminService;

    public AdminControllerImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public Flux<ApiResult<List<UserDTO>>> getUsers() {
        return adminService.getUsers();
    }

    @Override
    public Mono<ApiResult<UserDetailDTO>> getUserDetails(String id) {
        return adminService.getUserDetails(id);
    }

    @Override
    public Mono<ApiResult<UserDTO>> editUser(@Valid UserEditByAdminDTO userEditByAdminDTO) {
        return adminService.editUser(userEditByAdminDTO);
    }
}
