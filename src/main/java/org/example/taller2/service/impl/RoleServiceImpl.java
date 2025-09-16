package org.example.taller2.service.impl;

import org.example.taller2.entity.Permission;
import org.example.taller2.entity.Role;
import org.example.taller2.entity.RolePermission;
import org.example.taller2.entity.RolePermissionId;
import org.example.taller2.repository.PermissionRepository;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    @Transactional
    public void createRole(String name, String description, String permissionName) {
        Permission permission = permissionRepository.findByName(permissionName);
        if (permission == null) {
            throw new IllegalArgumentException("Permission not found: " + permissionName);
        }

        Role role = new Role(description, name);

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRolePermissionId(new RolePermissionId());
        rolePermission.setRole(role);
        rolePermission.setPermission(permission);

        role.getRolesPermissions().add(rolePermission);

        roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteRole(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new IllegalArgumentException("Role not found");
        }
        roleRepository.deleteById(roleId);
    }

    @Override
    @Transactional
    public void updateRole(Long roleId, String name, String description, String permissionName) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        Permission permission = permissionRepository.findByName(permissionName);
        if (permission == null) {
            throw new IllegalArgumentException("Permission not found: " + permissionName);
        }

        role.setName(name);
        role.setDescription(description);

        role.getRolesPermissions().clear();

        RolePermission newRolePermission = new RolePermission();
        newRolePermission.setRolePermissionId(new RolePermissionId());
        newRolePermission.setRole(role);
        newRolePermission.setPermission(permission);

        role.getRolesPermissions().add(newRolePermission);
    }
}