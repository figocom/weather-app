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
     private String oldPassword;
     private String newPassword;
     private String confirmNewPassword;
}




