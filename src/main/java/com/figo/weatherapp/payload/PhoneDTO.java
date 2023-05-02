package com.figo.weatherapp.payload;

import com.figo.weatherapp.utils.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
    @NotBlank(message = "{PHONE_NUMBER_SHOULD_NOT_BE_EMPTY}")
    @Pattern(regexp = AppConstant.PHONE_NUMBER_REGEX, message = "{PHONE_NUMBER_PATTERN}")
    private String phoneNumber;
}
