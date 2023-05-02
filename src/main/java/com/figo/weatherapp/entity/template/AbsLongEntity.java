package com.figo.weatherapp.entity.template;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
public abstract class AbsLongEntity extends AbsUserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
