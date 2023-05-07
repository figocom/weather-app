package com.figo.weatherapp.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.figo.weatherapp.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityWeatherDto {
    private Integer cityId;
    private String name;
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

    public static CityWeatherDto fromEntity(City city) {
        return CityWeatherDto.builder()
                .cityId(city.getId())
                .name(city.getName())
                .temperature_celsius(city.getTemperature_celsius())
                .temperature_fahrenheit(city.getTemperature_fahrenheit())
                .windSpeedKmh(city.getWindSpeedKmh())
                .windSpeedMph(city.getWindSpeedMph())
                .windDirection(city.getWindDirection())
                .humidity(city.getHumidity())
                .build();
    }
    public static List<CityWeatherDto> fromEntity(List<City> cities) {
        return cities.stream().map(CityWeatherDto::fromEntity).collect(Collectors.toList());
    }
}
