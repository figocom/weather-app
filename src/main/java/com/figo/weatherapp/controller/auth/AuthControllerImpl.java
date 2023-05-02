package com.figo.weatherapp.controller.auth;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthControllerImpl implements AuthController{

    @Override
    public ApiResult<CheckUserDTO> checkUserApplication(PhoneDTO phoneDTO) {
        return null;
    }

    @Override
    public ApiResult<SmsCodeDTO> sendApplicationSmsForRegisterPath(CodeWithPasswordDTO passwordDTO) {
        return null;
    }

    @Override
    public ApiResult<TokenDTO> signUpApplication(SignUpDTO signUpDTO) {
        return null;
    }

    @Override
    public ApiResult<SignInResponseDTO> signInApplication(SignInDTO signInDTO) {
        return null;
    }

    @Override
    public ApiResult<ForgotPasswordDTO> forgotApplicationPassword(PhoneDTO phoneDTO) {
        return null;
    }

    @Override
    public ApiResult<?> checkSmsCodeOrEmailCodeForSignInOrForForgotPassword(CodeWithPasswordDTO verificationCodeWithPasswordDTO) {
        return null;
    }

    @Override
    public ApiResult<TokenDTO> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        return null;
    }

    @Override
    public ApiResult<String> forgotLogin(EmailDTO emailDTO) {
        return null;
    }

    @Override
    public ApiResult<?> getCities() {
        return null;
    }

    @Override
    public Map<Object, Object> getValuesByLang(String language) {
        return null;
    }
}
