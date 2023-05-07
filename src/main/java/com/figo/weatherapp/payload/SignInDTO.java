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

    @NotBlank(message = "Ism bo'sh bo'lmasin")
      private String username;

    @NotBlank
    private String password;

}
