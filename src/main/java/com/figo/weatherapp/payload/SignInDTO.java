package com.figo.weatherapp.payload;

import com.figo.weatherapp.utils.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {

    @NotBlank
    @Pattern(regexp = AppConstant.PHONE_NUMBER_REGEX,
            message = "Telefon raqam formati xato. Telefon raqam + bilan boshlanib, 9 tadan 15 tagacha sonlarda iborat bo'lishi kerak")
    private String phoneNumber;

    @NotBlank
    private String password;

}
