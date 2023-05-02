package com.figo.weatherapp.entity;


import com.figo.weatherapp.entity.template.AbsLongEntity;
import com.figo.weatherapp.enums.RoleType;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@Entity(name = "role")
@Where(clause = "deleted=false")
@SQLDelete(sql = "UPDATE role SET deleted=true WHERE id=?")
public class Role extends AbsLongEntity {

    //MIJOZGA KO'RINADIGAN NOMI
    @Column(nullable = false)
    private String name;

    //ADMINGA KO'RINADI. BU ROLE HAQIDA IZOH YOZISHI MUMKIN
    @Column(length = 500)
    private String description;

    //TIZIMDA MENTORNI, ASSISTENTNI, STUDENTNI, OPERATORNI
    // SO'RGANDA TOPISHIMIZ UCHUN ZARUR CONSTANTLAR
    //(DROP INDEX if exists uk_role_role_enum;
    // CREATE UNIQUE INDEX uk_role_role_enum ON role (role_enum) WHERE role_enum<>'OTHER';)
    @Column(nullable = false, name = "role_type")
    @Enumerated(EnumType.STRING)
    private RoleType roleType;


}
