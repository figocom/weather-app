package com.figo.weatherapp.entity.template;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

/**
 * OBJECTNI OCHGAN YOKI UNI
 * O'ZGARTIRGAN USERNI OLIB BERISH UCHUN XIZMAT QILADI
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
public abstract class AbsUserAudit extends AbsDateAudit {

    @CreatedBy
    @Column("created_by_id")
    private Integer createdById;
    //OBJECTNI KIM OCHGANI.
    // AGAR U SECURITYCONTEXTDA BO'LSA YOZILADI AKS HOLDA NULL

    @LastModifiedBy
    @Column("updated_by_id")
    private Integer updatedById;
    //OBJECTNI KIM O'ZGARTIRGANI.
    // AGAR U SECURITYCONTEXTDA BO'LSA YOZILADI AKS HOLDA NULL
}
