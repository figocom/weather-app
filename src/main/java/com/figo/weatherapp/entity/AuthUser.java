package com.figo.weatherapp.entity;

import com.figo.weatherapp.entity.template.AbsUUIDUserAuditEntity;
import com.figo.weatherapp.utils.TableNameConstant;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.*;

/**
 * @author Murtozayev Manguberdi
 * @apiNote
 * @since 01.05.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@SQLDelete(sql = ("update " + TableNameConstant.AUTH_USER + " set deleted=true where id=?"))
@Where(clause = "deleted=false")
@Entity(name = TableNameConstant.AUTH_USER)
public class AuthUser extends AbsUUIDUserAuditEntity implements UserDetails {
    @Column(name = "first_name")
    private String firstName;

    //FAMILYASI
    @Column(name = "last_name")
    private String lastName;

    //TELEFON RAQAMI
    @Column(nullable = false, name = "phone_number")
    private String phoneNumber;

    //TIZIMGA KIRUVCHI PAROLI
    private String password;
    //ASOSIY EMAILI
    private String email;
    // USERNI  QAYSI SHAXARLARGA OBUNA BOLGANLIGI
    @Column(name = "company_list", columnDefinition = "bigint[]")
    @Type(type = "com.figo.weatherapp.type.GenericLongArrayType")
    private Long[] subscriptionCityList;

    //ASOSIY EMAILIGA YUBORILGAN TASDQILASH KODI
    @Column(name = "email_code")
    private String emailCode;
    //AVATARDAGI RASM URL
    @Column(name = "photo_id")
    private String photoId;

    @Column(name = "birth_date")
    private Date birthDate;


    @Column(name = "account_non_expired")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;

    private boolean enabled = true;
    private boolean admin;
    @ManyToOne
    private Role role;

    //USER TANLAB TURGAN ACTIVE TIL
    @OneToOne
    private Language activeLanguage;

    private AuthUser(String firstName, String lastName, String phoneNumber, String password, Boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.admin = admin;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return this.phoneNumber;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AuthUser user = (AuthUser) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    public List<Long> getCityList() {
        if (subscriptionCityList == null)
            return new ArrayList<>();
        return List.of(subscriptionCityList);
    }

    public void setCompanyList(List<Long> companyList) {
        this.subscriptionCityList = companyList.toArray(new Long[0]);
    }

}
