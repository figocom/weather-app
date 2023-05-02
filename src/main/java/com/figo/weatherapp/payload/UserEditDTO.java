package com.figo.weatherapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserEditDTO {
    private UUID id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
    @NotBlank
    private String currentPassword;

    //TELEFON RAQAMI
    private String phoneNumber;

    @Email(message = "{EMAIL_PATTERN}")
    private String email;

}
