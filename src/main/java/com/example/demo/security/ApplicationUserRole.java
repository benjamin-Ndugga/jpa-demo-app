package com.example.demo.security;

import static com.example.demo.security.ApplicationUserPermission.*;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author benjie_en
 */
public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(USER_READ, ADMIN_READ, ADMIN_WRITE)),
    USER(Sets.newHashSet(USER_READ));

    private final Set<ApplicationUserPermission> permissions;

    private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public  Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
