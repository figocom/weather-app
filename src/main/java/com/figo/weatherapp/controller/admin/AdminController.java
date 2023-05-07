package com.figo.weatherapp.controller.admin;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.payload.UserDetailDTO;
import com.figo.weatherapp.payload.UserEditByAdminDTO;
import com.figo.weatherapp.utils.AppConstant;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(path = AdminController.AdminControllerPath)
public interface AdminController {
    String AdminControllerPath= AppConstant.BASE_PATH_V1+"admin/";
    String GET_USER_LIST="user-list";
    String GET_USER_DETAILS="user-details/{id}";
    String EDIT_USER="edit-user";
    //write documentation

    @GetMapping(GET_USER_LIST)
    Flux<ApiResult<List<UserDTO>>> getUsers();

    @GetMapping(GET_USER_DETAILS)
    Mono<ApiResult<UserDetailDTO>> getUserDetails( @PathVariable String id);
    @PatchMapping(EDIT_USER)
    Mono<ApiResult<UserDTO>> editUser(@Valid @RequestBody UserEditByAdminDTO userEditByAdminDTO);

}
