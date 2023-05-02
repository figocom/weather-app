package com.figo.weatherapp.payload;


import com.figo.weatherapp.utils.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO extends SmsCodeDTO {
    //    @NotBlank(message = "Ism bo'sh bo'lmasin")
    private String firstName;
    //
//    @NotBlank(message = "Familya bo'sh bo'lmasin")
    private String lastName;

    @NotBlank(message = "{PHONE_NUMBER_SHOULD_NOT_BE_EMPTY}")
    @Pattern(regexp = AppConstant.PHONE_NUMBER_REGEX,
            message = "Telefon raqam formati xato. Telefon raqam + bilan boshlanib, 9 tadan 15 tagacha sonlarda iborat bo'lishi kerak")
    private String phoneNumber;

//    @Pattern(regexp = RestConstants.PASSWORD_UNIVERSITY_REGEX, message = "Parolda 6 tadan ko'p 15 tadan kam belgi bo'lishi shart ")
    private String password;

//    @Pattern(regexp = RestConstants.PASSWORD_UNIVERSITY_REGEX, message = "Parolda 6 tadan ko'p 15 tadan kam belgi bo'lishi shart ")
    private String prePassword;

    private String voucherCode;
    private String registerPath;
}
