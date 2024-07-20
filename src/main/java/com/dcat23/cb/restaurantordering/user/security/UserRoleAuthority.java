package com.dcat23.cb.restaurantordering.user.security;

import com.dcat23.cb.restaurantordering.user.model.Role;
import org.springframework.security.core.GrantedAuthority;

public record UserRoleAuthority(Role role) implements GrantedAuthority {

//    @Serial
//    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * @return role name
     */
    @Override
    public String getAuthority() {
        return "ROLE_" + role.name();
    }
}
