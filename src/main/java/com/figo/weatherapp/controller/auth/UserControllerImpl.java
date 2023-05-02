package com.figo.weatherapp.controller.auth;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.ResetPasswordDTO;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.payload.UserEditDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{
    @Override
    public ApiResult<UserDTO> getApplicationUserMe() {
        return null;
    }

    @Override
    public ApiResult<String> editUserSelf(UserEditDTO userEditDTO) {
        return null;
    }

    @Override
    public ApiResult<String> editUserPassword(ResetPasswordDTO passwordEditDTO) {
        return null;
    }

    @Override
    public ApiResult<Boolean> logOut() {
        return null;
    }

    @Override
    public void changeActiveLanguage() {

    }
}
