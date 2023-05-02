package com.figo.weatherapp.service;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;

import java.util.Map;

public interface AuthService {

    ApiResult<CheckUserDTO> checkUserApplication(PhoneDTO phoneDTO);
    ApiResult<TokenDTO> signUpApplication(SignUpDTO signUpDTO);

    ApiResult<?> getCities();

    ApiResult<SignInResponseDTO> signInApplication(SignInDTO signInDTO);
    ApiResult<ForgotPasswordDTO> forgotPasswordApplication(PhoneDTO phoneDTO);

    ApiResult<?> checkSmsCodeOrEmailCodeForSignInOrForForgotPassword(CodeWithPasswordDTO codeWithPasswordDTO);


    ApiResult<TokenDTO> resetPassword(ResetPasswordDTO resetPasswordDTO);

    ApiResult<String> forgotLogin(EmailDTO email);


    void phoneNumberIfExistThrow(String phoneNumber);



    Map<Object, Object> getValuesByLang(String language);

}
