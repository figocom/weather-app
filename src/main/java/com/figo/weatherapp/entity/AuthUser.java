package com.figo.weatherapp.entity;

import com.figo.weatherapp.entity.template.AbsIntEntity;
import com.figo.weatherapp.utils.TableNameConstant;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author Murtozayev Manguberdi
 * @apiNote
 * @since 01.05.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = TableNameConstant.AUTH_USER)
public class AuthUser extends AbsIntEntity implements UserDetails {
    @Column("first_name")
    private String firstName;

    //FAMILYASI
    @Column("last_name")
    private String lastName;

    //TELEFON RAQAMI
    @Column( "username")
    private String username;

    //TIZIMGA KIRUVCHI PAROLI
    private String password;
    @Column( "account_non_expired")
    private boolean accountNonExpired = true;

    @Column("account_non_locked")
    private boolean accountNonLocked = true;
    @Column("credentials_non_expired")
    private boolean credentialsNonExpired = true;

    private boolean enabled = true;

    private String role;


    private AuthUser(String firstName, String lastName, String username, String password, Boolean admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }


}
