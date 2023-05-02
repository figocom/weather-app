package com.figo.weatherapp.entity;

import com.figo.weatherapp.entity.template.AbsLongEntity;
import com.figo.weatherapp.enums.PermissionEnum;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@Entity(name = "permission")
@Where(clause = "deleted=false")
@SQLDelete(sql = "update permission set deleted=true where id=?")
public class Permission extends AbsLongEntity {

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissionEnum permissionEnum;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Permission that = (Permission) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
