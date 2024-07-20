package com.dcat23.cb.restaurantordering.user.model;

import com.dcat23.cb.restaurantordering.user.security.UserRoleAuthority;
import org.springframework.security.core.GrantedAuthority;

public enum Role {
    ADMIN,
    CUSTOMER,
    STAFF;

    public GrantedAuthority authority() {
        return new UserRoleAuthority(this);
    }
}
