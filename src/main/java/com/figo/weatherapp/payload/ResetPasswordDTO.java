package com.figo.weatherapp.payload;


import com.figo.weatherapp.utils.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordDTO {

    //AGAR MIJOZ SMS ORQALI PAROLNI TIKLASA, MIJOZGA YUBORILGAN SMS CODE ID SI
    private UUID smsCodeId;

    //AGAR MIJOZ SMS ORQALI PAROLNI TIKLASA, MIJOZGA YUBORILGAN SMS CODE
    @Pattern(regexp = AppConstant.SMS_CODE_REGEX, message = "{BAD_REQUEST}")
    private String smsCode;


    //AGAR MIJOZ EMAIL ORQALI PAROLNI TIKLASA, EMAILGA YUBORILGAN TASDIQLASH KODI
    @Pattern(regexp = AppConstant.SMS_CODE_REGEX, message = "{BAD_REQUEST}")
    private String emailCode;
}




