package com.figo.weatherapp.controller.auth;


import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.*;
import com.figo.weatherapp.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping(value = AuthController.AUTH_CONTROLLER_PATH)
public interface AuthController {

    String AUTH_CONTROLLER_PATH = AppConstant.BASE_PATH_V1 + "auth/";

    String APPLICATION_CHECK_PHONE_PATH = "application/check-phone";
    String SEND_APPLICATION_SMS_FOR_REGISTER_PATH = "application/send-sms-for-register";
    String APPLICATION_SIGN_UP_PATH = "application/sign-up";
    String APPLICATION_SIGN_IN_PATH = "application/sign-in";
    String FORGOT_APPLICATION_PASSWORD_PATH = "application/forgot-password";
    String CHECK_SMS_CODE_OR_EMAIL_CODE_FOR_SIGN_IN_OR_FORGOT_PASSWORD_PATH = "check-code";
    String RESET_PASSWORD_PATH = "reset-password";
    String FORGOT_LOGIN_PATH = "forgot-login";
    String GET_CITIES = "cities";
    String GET_VALUES_BY_LANGUAGE_PATH = "/language/{language}";

    /**
     * USER TIZIMGA KIRISHNI TANLAGANDA AVVAL TELEFON RAQAMNI YUBORADI. AGAR BUNDAY USER BO'LSA VA UNI PASSWORDI O'RNATILGAN BO'LSA,
     * registered va hasPassword FILDLARINI true QILIB QAYTARAMIZ, RO'YXATDAN O'TGAN-U AMMO PAROLI O'RNATILMAGAN BO'LSA registered TRUE va hasPassword FALSE QILIB QAYTARAMIZ.
     * AKS HOLDA registered va hasPassword FIELDLARINI FALSE HOLATDA QAYTARAMIZ
     *
     * @param phoneDTO @RequestBody
     * @return {@link ApiResult}<{@link CheckUserDTO}>
     */
    @PostMapping(value = APPLICATION_CHECK_PHONE_PATH)
    ApiResult<CheckUserDTO> checkUserApplication(@Valid @RequestBody PhoneDTO phoneDTO);


    /**
     * USER RO'YXATDAN O'TAYOTGANDA TELEFON RAQAM AVVAL TIZIMDA BORLIGI VA YOKI
     * PAROLLARINI BIZNING QOIDA BO'YICHA EKANLIGI TEKSHIRILADI.
     * AGAR BUNDAY TELEFON RAQAMLI USER BO'LMASA VA PAROLLAR MOS BO'LSA USHBU TELEFON RAQAMGA SMS YUBORILADI
     *
     * @param passwordDTO @RequestBody
     * @return ApiResult<SmsCodeDTO>
     */
    @PostMapping(SEND_APPLICATION_SMS_FOR_REGISTER_PATH)
    ApiResult<SmsCodeDTO> sendApplicationSmsForRegisterPath(@Valid @RequestBody CodeWithPasswordDTO passwordDTO);


    @PostMapping(APPLICATION_SIGN_UP_PATH)
    ApiResult<TokenDTO> signUpApplication(@Valid @RequestBody SignUpDTO signUpDTO);

    /**
     * App UCHUN SIGN IN
     */
    @PostMapping(APPLICATION_SIGN_IN_PATH)
    ApiResult<SignInResponseDTO> signInApplication(@Valid @RequestBody SignInDTO signInDTO);

    /**
     * USER PAROLINI UNUTGANDA (1-bosqich):
     * BUNDA TELEFON RAQAM KIRITILADI. USER PAROLNI 3 XIL YO'L BILAN TIKLASHI MUMKIN: BULAR
     * 1) TELEFONGA SMS YUBORISH ORQALI
     * 2) EMAIL GA XABAR YUBORISH ORQALI
     * 3) XAVFSIZLIK SAVOLLARIGA(MAXFIY SAVOLLAR) JAVOB BERISH ORQALI
     * AGAR USHBU TELEFON RAQAMLI USER BO'LADIGAN BO'LSA, QUYDAGI HOLATLARDAN BIRI BO'LADI:
     * 1. AGAR USERNING EMAILI BO'LSA showEmail TRUE, XAVFSIZLIK SAVOLLARIGA BERGAN JAVOBLARI BO'LSA showQuestion TRUE VA showPhoneNumber TRUE QAYTADI;
     * 2. AGAR USERNING FAQAT EMAILI BO'LSA showEmail TRUE VA showPhoneNumber TRUE QAYTADI;
     * 3. AGAR USERNING FAQAT XAVFSIZLIK SAVOLLARIGA BERGAN JAVOBLARI BO'LSA showQuestion TRUE QAYTADI VA showPhoneNumber TRUE QAYTADI
     * 4. AGAR USERNING EMAILI VA XAVFSIZLIK SAVOLLARIGA BERGAN JAVOBLARI BO'LMASA, TELEFONIGA SMS YUBORILADI VA FRONTENDGA sentSms TRUE VA smsCode OBJECTI ICHIDA smsCodeId FIELDI KETADI;
     *
     * @param phoneDTO @RequestBody
     * @return ApiResult<ForgotPasswordDTO>
     */
    @PostMapping(FORGOT_APPLICATION_PASSWORD_PATH)
    ApiResult<ForgotPasswordDTO> forgotApplicationPassword(@Valid @RequestBody PhoneDTO phoneDTO);



    /**
     * <h4>TELEFONGA KELGAN SMS NI TASDIQLASH ORQALI KIRISH YOKI PAROLNI UNUTGANDA SMS TO'G'RLIGINI TEKSHIRISH UCHUN</h4>
     * BU YO'L QUYIDAGI 2 TA ISHDAN BIRINI BAJARADI:<p>
     * 1. SMS NI TASDIQLASH ORQALI TIZIMGA KIRISH (TOKEN OLISH)<p>
     * 2. SMS NI TASDIQLASH ORQALI PAROLNI TIKLASH
     * <h4>EMAILGA KELGAN KODNI NI TASDIQLASH ORQALI KIRISH YOKI PAROLNI UNUTGANDA EMAILGA KELGAN KOD TO'G'RLIGINI TEKSHIRISH UCHUN</h4>
     * BU YO'L QUYIDAGI 2 TA ISHDAN BIRINI BAJARADI:<p>
     * 1. EMAILGA KELGAN KODNI TASDIQLASH ORQALI TIZIMGA KIRISH (TOKEN OLISH)<p>
     * 2. EMAILGA KELGAN KODNI NI TASDIQLASH ORQALI PAROLNI TIKLASH
     *
     * @param verificationCodeWithPasswordDTO @RequestBody
     * @return ApiResult<SmsCodeDTO>
     */
    @PostMapping(CHECK_SMS_CODE_OR_EMAIL_CODE_FOR_SIGN_IN_OR_FORGOT_PASSWORD_PATH)
    ApiResult<?> checkSmsCodeOrEmailCodeForSignInOrForForgotPassword(@Valid @RequestBody CodeWithPasswordDTO verificationCodeWithPasswordDTO);
    @PostMapping(path = RESET_PASSWORD_PATH)
    ApiResult<TokenDTO> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO);

    /**
     * USER LOGINNI UNUTGANDA, EMAIL KELADI. USHBU EMAILGA ACCOUNT OCHILGAN BO'LSA, EMAILGA TELEFON RAQAM XABAR QILIB YUBORILADI
     *
     * @param emailDTO @RequestParam
     * @return ApiResult<String>
     */
    @PostMapping(path = FORGOT_LOGIN_PATH)
    ApiResult<String> forgotLogin(@Valid @RequestBody EmailDTO emailDTO);
 @GetMapping(path = GET_CITIES)
    ApiResult<?> getCities();

    @GetMapping(path = GET_VALUES_BY_LANGUAGE_PATH)
    Map<Object, Object> getValuesByLang(@PathVariable String language);

}
