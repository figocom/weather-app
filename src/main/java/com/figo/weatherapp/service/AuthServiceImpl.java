package com.figo.weatherapp.service;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  UserDetailsService  , AuthService{


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public ApiResult<CheckUserDTO> checkUserApplication(PhoneDTO phoneDTO) {
        return null;
    }

    @Override
    public ApiResult<TokenDTO> signUpApplication(SignUpDTO signUpDTO) {
        return null;
    }

    @Override
    public ApiResult<?> getCities() {
        return null;
    }

    @Override
    public ApiResult<SignInResponseDTO> signInApplication(SignInDTO signInDTO) {
        return null;
    }

    @Override
    public ApiResult<ForgotPasswordDTO> forgotPasswordApplication(PhoneDTO phoneDTO) {
        return null;
    }

    @Override
    public ApiResult<?> checkSmsCodeOrEmailCodeForSignInOrForForgotPassword(CodeWithPasswordDTO codeWithPasswordDTO) {
        return null;
    }

    @Override
    public ApiResult<TokenDTO> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        return null;
    }

    @Override
    public ApiResult<String> forgotLogin(EmailDTO email) {
        return null;
    }

    @Override
    public void phoneNumberIfExistThrow(String phoneNumber) {

    }

    @Override
    public Map<Object, Object> getValuesByLang(String language) {
        return null;
    }
}