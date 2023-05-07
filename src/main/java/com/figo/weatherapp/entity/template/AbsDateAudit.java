package com.figo.weatherapp.entity.template;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * OBJECTLAR DB GA QO'SHILGANDA YOKI O'ZGARTIRILGANDA
 * AVTOMAT RAVISHDA O'SHA VAQTNI OLISHI UCHUN
 */
@Getter
@Setter
@ToString

@FieldNameConstants
public abstract class AbsDateAudit implements Serializable {
    @Column("created_at")
    private Timestamp createdAt;//OBJECT YANGI OCHIGANDA ISHLATILADI
    @Column("updated_at")
    private Timestamp updatedAt;//OBJECT O'ZGARGANDA ISHLAYDI

    private Boolean deleted = false;

}
