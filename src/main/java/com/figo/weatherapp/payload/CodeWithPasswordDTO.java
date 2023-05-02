package com.figo.weatherapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.figo.weatherapp.enums.VerificationTypeEnum;
import com.figo.weatherapp.utils.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

//USHBU CLASSNI MIJOZGA SMS YUBORGACH UNGA XABAR UCHUN
// VA MIJOZDAN BIZGA SMS CODE NI YUBORISHI UCHUN HAM ISHLATAMIZ
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeWithPasswordDTO {

    //MIJZOGA YUBORGAN SMS KOD NING ID SI
    private UUID smsCodeId;

    //MIJOZGA BORGAN SMS KODI
    @NotNull(message = "Kod bo'sh bo'lmasin")
    private String smsCode;

    //MIJOZ KIRAYOTGAN QURILMASINI ISHONCHLI DEB BELGILAGAN BO'LSA
    private Boolean reliableDevice;

    //PAROL UNUTGANDA TIKLASH UCHUN BO'LSA TRUE BO'LADI
    private boolean forgotPassword;

    //QAYSI TELEFON RAQAMLI USER KIRYAPTI
    @NotBlank
    @Pattern(regexp = AppConstant.PHONE_NUMBER_REGEX,
            message = "Telefon raqam formati xato. Telefon raqam + bilan boshlanib, 9 tadan 15 tagacha sonlarda iborat bo'lishi kerak")
    private String phoneNumber;

    //SIGN IN QILAYOTGANDA KELADI PAROL FAQAT
//    @Pattern(regexp = RestConstants.PASSWORD_REGEX, message = "Parolda kamida bitta katta harf, kamida bitta kichik harf, kamida bitta raqam va !@#$%^&*()_+= belgilardan biri ishlatilishi kerak ")
    private String password;

//    @NotNull(message = "Verification type ni berish kerak")
    private VerificationTypeEnum verificationType;

    //BIZGA BROWSERDA TURGAN DEVICE KEY KELADI
    private UUID deviceKey;
}
