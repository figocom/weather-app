package com.figo.weatherapp.entity.template;

import com.figo.weatherapp.utils.ColumnKey;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * OBJECTLAR DB GA QO'SHILGANDA YOKI O'ZGARTIRILGANDA
 * AVTOMAT RAVISHDA O'SHA VAQTNI OLISHI UCHUN
 */
@Getter
@Setter
@ToString
@MappedSuperclass
@FieldNameConstants
public abstract class AbsDateAudit implements Serializable {
    @CreationTimestamp
    @Column(updatable = false, name = ColumnKey.CREATED_AT)
    private Timestamp createdAt;//OBJECT YANGI OCHIGANDA ISHLATILADI

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;//OBJECT O'ZGARGANDA ISHLAYDI

    private Boolean deleted = false;

}
