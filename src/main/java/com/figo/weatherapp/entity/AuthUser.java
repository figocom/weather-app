package com.figo.weatherapp.entity;

import com.figo.weatherapp.entity.template.AbsUUIDUserAuditEntity;
import com.figo.weatherapp.utils.TableNameConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * @author Murtozayev Manguberdi
 * @apiNote
 * @since 01.05.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = ("update " + TableNameConstant.AUTH_USER + " set deleted=true where id=?"))
@Where(clause = "deleted=false")
@Entity(name = TableNameConstant.AUTH_USER)
public class AuthUser extends AbsUUIDUserAuditEntity {
    private String username;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthUser authUser = (AuthUser) o;
        return getId() != null && Objects.equals(getId(), authUser.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
