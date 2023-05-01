package com.figo.weatherapp.entity.template;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * OBJECTNI OCHGAN YOKI UNI
 * O'ZGARTIRGAN USERNI OLIB BERISH UCHUN XIZMAT QILADI
 */
@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)//2-ishimiz
@EqualsAndHashCode(callSuper = true)
@FieldNameConstants
public abstract class AbsUserAudit extends AbsDateAudit {

    @CreatedBy
    @Column(name = "created_by_id", updatable = false)
    private UUID createdById;
    //OBJECTNI KIM OCHGANI.
    // AGAR U SECURITYCONTEXTDA BO'LSA YOZILADI AKS HOLDA NULL

    @LastModifiedBy
    @Column(name = "updated_by_id")
    private UUID updatedById;
    //OBJECTNI KIM O'ZGARTIRGANI.
    // AGAR U SECURITYCONTEXTDA BO'LSA YOZILADI AKS HOLDA NULL
}
