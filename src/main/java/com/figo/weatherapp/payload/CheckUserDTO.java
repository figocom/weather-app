package com.figo.weatherapp.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * USHBU CLASSNI MIJOZGA SMS YUBORGACH UNGA XABAR UCHUN
 * VA MIJOZDAN BIZGA SMS CODE NI YUBORISHI UCHUN HAM ISHLATAMIZ
 **/
@Data
@NoArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckUserDTO {

    private Boolean registered = true;

    private Boolean hasPassword;

    private CheckUserDTO(Boolean registered) {
        this.registered = registered;
    }

    private CheckUserDTO(Boolean registered, Boolean hasPassword) {
        this.registered = registered;
        this.hasPassword = hasPassword;
    }

    public static CheckUserDTO hasRegisteredFalse() {
        return new CheckUserDTO(false);
    }


    public static CheckUserDTO hasPassword(Boolean hasPassword) {
        return new CheckUserDTO(true, hasPassword);
    }
}
