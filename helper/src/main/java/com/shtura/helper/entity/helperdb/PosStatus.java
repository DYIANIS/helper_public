package com.shtura.helper.entity.helperdb;

import org.springframework.security.core.GrantedAuthority;

public enum PosStatus implements GrantedAuthority {
    DEFAULT, RUNNING, STOPPED;

    @Override
    public String getAuthority() {
        return toString();
    }
}
