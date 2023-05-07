package com.figo.weatherapp.payload;

import com.figo.weatherapp.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityCreatedDTO {
    @NotBlank(message = "City name cannot be blank")
    private String name;
    @Builder.Default
    private boolean enabled=true;

    public City toEntity() {
        return City.builder()
                .name(name)
                .enabled(enabled)
                .build();
    }
}
