package com.figo.weatherapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.figo.weatherapp.enums.VerificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

//USHBU CLASSNI MIJOZGA SMS YUBORGACH UNGA XABAR UCHUN
// VA MIJOZDAN BIZGA SMS CODE NI YUBORISHI UCHUN HAM ISHLATAMIZ
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SmsCodeDTO {

    //MIJZOGA YUBROGAN SMS CODE IMIZNING ID SI
    @NotNull(message = "SMS code id maydoni bo'sh bo'lmasin")
    private UUID smsCodeId;

    @NotNull(message = "SMS bo'sh bo'lmasin")
    private String smsCode;

    private boolean reliableDevice;

    //USERGA SMS YUBORILGACH QAYTADIGAN XABAR.(+99899 6** ** 36 RAQAMIGA 6 TA RAQAMDAN IBORAT BO'LGAN KOD YUBORDIK KABI)
    private String abbreviationMessage;

    private VerificationTypeEnum verificationType=VerificationTypeEnum.EMAIL;
}






