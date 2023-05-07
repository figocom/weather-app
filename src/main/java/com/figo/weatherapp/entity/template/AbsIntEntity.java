package com.figo.weatherapp.entity.template;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public abstract class AbsIntEntity extends AbsUserAudit {
    @Id
    private Integer id;
}
