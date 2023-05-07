package com.figo.weatherapp.payload;


import com.figo.weatherapp.utils.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {
      @NotBlank(message = "Ism bo'sh bo'lmasin")
    private String firstName;
     @NotBlank(message = "Familya bo'sh bo'lmasin")
    private String lastName;

    @NotBlank(message = "{USER_NAME_SHOULD_NOT_BE_EMPTY}")
      private String username;
    @NotBlank(message = "Parol bo'sh bo'lmasin")
     private String password;
    @NotBlank(message = "Parolni qayta kiriting")
     private String prePassword;
}
