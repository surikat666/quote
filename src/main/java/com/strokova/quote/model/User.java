package com.strokova.quote.model;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;

import static java.util.Collections.singleton;

@Entity
@Table(name = "users")
@Setter
public class User implements UserDetails {

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @Column
    private String username;
    @Column
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
