package com.shtura.helper.service.security;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.shtura.helper.entity.Role;
import com.shtura.helper.entity.User;

public class UserPrincipal implements UserDetails {

    public UserPrincipal(User user) {
        super();
        this.user = user;
    }

    private User user;

    @Override
    public List<Role> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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

    public long getId() {
        return user.getId();
    }
}
