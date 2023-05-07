package com.figo.weatherapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CityDTO {
    @JsonProperty("temp_c")
    private Integer temperature_celsius;
    @JsonProperty("temp_f")
    private Integer temperature_fahrenheit;
    @JsonProperty("wind_kph")
    private Integer windSpeedKmh;
    @JsonProperty("wind_mph")
    private Integer windSpeedMph;
    @JsonProperty("wind_dir")
    private String windDirection;
    @JsonProperty("humidity")
    private Integer humidity;

    public static CityDTO fromEntity(City city) {
        return CityDTO.builder()
                .temperature_celsius(city.getTemperature_celsius())
                .temperature_fahrenheit(city.getTemperature_fahrenheit())
                .windSpeedKmh(city.getWindSpeedKmh())
                .windSpeedMph(city.getWindSpeedMph())
                .windDirection(city.getWindDirection())
                .humidity(city.getHumidity())
                .build();
    }
}
