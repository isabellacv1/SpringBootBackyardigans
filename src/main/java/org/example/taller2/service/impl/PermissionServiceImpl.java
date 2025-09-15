package org.example.taller2.service.impl;

import org.example.taller2.entity.Permission;
import org.example.taller2.repository.PermissionRepository;
import org.example.taller2.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission createPermission(String name, String description) {
        Permission permission = new Permission();
        permission.setName(name);
        permission.setDescription(description);

        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Long id, String newName, String newDescription) {
        Optional<Permission> existingPermissionOpt = permissionRepository.findById(id);

        if (existingPermissionOpt.isEmpty()) {
            throw new IllegalArgumentException("Permission not found");
        }

        Permission existingPermission = existingPermissionOpt.get();
        existingPermission.setName(newName);
        existingPermission.setDescription(newDescription);

        return permissionRepository.save(existingPermission);
    }

    @Override
    public void deletePermission(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new IllegalArgumentException("Permission not found");
        }

        permissionRepository.deleteById(id);
    }
}