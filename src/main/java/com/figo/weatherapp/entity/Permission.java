package com.figo.weatherapp.entity;

import com.figo.weatherapp.entity.template.AbsLongEntity;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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

    //FRONTENDCHI SHU NAME BILAN ISHLAYDI
    @Column(nullable = false)
    private String name;//(studentHome, studentList, mentorHome...)
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
