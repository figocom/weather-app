package com.figo.weatherapp.entity;

import com.figo.weatherapp.entity.template.AbsUUIDUserAuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE language SET deleted=true WHERE id=?")
public class Language extends AbsUUIDUserAuditEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String code;

    private boolean active;
}
