package com.figo.weatherapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

//BU CLASS CLIENT SIGN IN QILGANDA UNGA SMS YUBORAMIZMI YOKI TOKEN QAYTARAMIZMI?
// SHUNI BERADIGAN OBJECT
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignInResponseDTO {

    //AGAR MIJOZGA SMS YUBORILGAN BO'LSA USHBU FIELD QAYTADI, QOLGAN FILDLAR QAYTAMAYDI
    private SmsCodeDTO smsCode;

    private List<Map<String, String>> options;

    //AGAR MIJOZGA SMS YUBRILMASA USHBU FIELD QAYTADI, QOLGAN FILDLAR QAYTAMAYDI
    private TokenDTO token;

    private boolean hasToken;

}
