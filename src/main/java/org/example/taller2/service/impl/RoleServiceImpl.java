package org.example.taller2.service.impl;

import org.example.taller2.entity.Permission;
import org.example.taller2.entity.Role;
import org.example.taller2.entity.RolePermission;
import org.example.taller2.repository.PermissionRepository;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    public void createRole(String name, String description, String permission) {
        Permission rolePermission = permissionRepository.findById(permission);

        if (rolePermission == null) {
            throw new IllegalArgumentException("Permission not found");
        }

        Role role = new Role(name, description);
        role = roleRepository.save(role);

        RolePermission rolePermissionObj = new RolePermission();
        rolePermissionObj.setRole(role);
        rolePermissionObj.setPermission(rolePermission);

        role.getRolesPermissions().add(rolePermissionObj);

        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            throw new IllegalArgumentException("Role not found");

        }
        roleRepository.delete(role);
    }

    @Override
    public void updateRole(Long roleId, String name, String description, String permission) {

        Role role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            throw new IllegalArgumentException("Role not found");
        }

        Permission rolePermission = permissionRepository.findById(permission);
        if (rolePermission == null) {
            throw new IllegalArgumentException("Permission not found");
        }

        role.setName(name);
        role.setDescription(description);

        role.getRolesPermissions().clear();

        RolePermission rolePermissionObj = new RolePermission();
        rolePermissionObj.setRole(role);
        rolePermissionObj.setPermission(rolePermission);
        role.getRolesPermissions().add(rolePermissionObj);

        roleRepository.save(role);
    }
}

