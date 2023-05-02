package com.figo.weatherapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

/**
 * USHBU CLASSNI MIJOZ PAROLNI UNUTGANDA UNGA QAYSI TANLOVLAR ORQALI PAROLNI TIKLASHNI TAKLIF ETADI.
 * AGAR MIJZODA XAVFSIZLIK SAVOLLARI VA EMAIL BO'LMASA smsCode OBJECTIGA SMS HAQIDA MA'LUMOTLAR YUBORADI
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForgotPasswordDTO {

    //RES
    //MIJOZNING EMAIL BO'LMASA VA XAVFSIZLIK SAVOLLARIGA JAVOB BERMAGAN BO'LMASA, SMS YUBORILADI.
    // USHBU OBJECT YUBORILADI SMS GA TEGISHLI MA'LUMOTLAR YUBORILADI
    private SmsCodeDTO smsCode;

    //MIJOZNING EMAIL BO'LSA YOKI XAVFSIZLIK SAVOLLARIGA JAVOB BERGAN BO'LSA,
    // USHBU LIST BO'LADI. QUYIDAGICHA OBJECT KETADI
    // "options": [
    //            {
    //                "phoneNumber": "+99899*****1136",
    //                "labelText": "Telefon raqamga kod yuborish"
    //            },
    //            {
    //                "labelText": "Emailga kod yuborish",
    //                "email": "si*****t@gmail.com"
    //            },
    //            {
    //                "question": "Xavfsizlik savollari",
    //                "labelText": "Xavfsizlik savollari orqali"
    //            }
    //        ]
    //
    private List<Map<String, String>> options;
}
