package com.figo.weatherapp.service.admin;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.payload.UserDetailDTO;
import com.figo.weatherapp.payload.UserEditByAdminDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AdminService {
    Flux<ApiResult<List<UserDTO>>> getUsers();

    Mono<ApiResult<UserDetailDTO>> getUserDetails(String id);

    Mono<ApiResult<UserDTO>> editUser(UserEditByAdminDTO userEditByAdminDTO);
}
