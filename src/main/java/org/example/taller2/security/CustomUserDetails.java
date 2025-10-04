package org.example.taller2.security;

import org.example.taller2.entity.RolePermission;
import org.example.taller2.entity.User;
import org.example.taller2.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private User user;
    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        var rolesAuthorities = user.getUserRoles().stream().map(
                userRole -> new SimpleGrantedAuthority(userRole.getRole().getName())
        ).toList();

        var rolesOfUser = user.getUserRoles().stream()
                .map (
                        UserRole::getRole
                ).toList();

        var permissions = rolesOfUser.stream()
                .flatMap(role -> role.getRolePermissions().stream())
                .map(RolePermission::getPermission)
                .toList();

        var permissionAuthorities = permissions.stream().map(
                permission -> new SimpleGrantedAuthority(permission.getName())
        ).toList();

        var fullAuthorities = new ArrayList<SimpleGrantedAuthority>();
        fullAuthorities.addAll(rolesAuthorities);
        fullAuthorities.addAll(rolesAuthorities);

        return fullAuthorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
