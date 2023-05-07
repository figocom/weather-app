package com.figo.weatherapp.entity;

import com.figo.weatherapp.entity.template.AbsIntEntity;
import com.figo.weatherapp.utils.TableNameConstant;
import io.micrometer.core.instrument.config.NamingConvention;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = TableNameConstant.CITY)
public class City extends AbsIntEntity {
    private String name;
    private boolean enabled;
    private Integer temperature_celsius;
    private Integer temperature_fahrenheit;
    @Column("windspeedkmh")
    private Integer windSpeedKmh;
    @Column("windspeedmph")
    private Integer windSpeedMph;
    @Column("winddirection")
    private String windDirection;

    private Integer humidity;


}
