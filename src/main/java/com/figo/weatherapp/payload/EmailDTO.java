package com.figo.weatherapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


//EMAILNI YUBORISH UCHUN
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    //FAQAT SIGN IN QILAYOTGANDA KELADI PAROL, FORGOT QILISHDA KELMAYDI
    @NotBlank(message = "{EMAIL_SHOULD_NOT_BE_EMPTY}")
    @Email(message = "{EMAIL_PATTERN}")
    private String email;

    private String title;

    private String link;

    private String message;

}
