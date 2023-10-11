package com.shtura.helper.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    DEFAULT, ADMIN, IT, PO, DEMONSTRATION;

    @Override
    public String getAuthority() {
        return "ROLE_" + toString();
    }
}
