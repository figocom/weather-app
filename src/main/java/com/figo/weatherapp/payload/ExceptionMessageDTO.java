package com.figo.weatherapp.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessageDTO implements Serializable {
    private UserDTO user;
    private String message;
    private String serviceName;
    private String url;
}

