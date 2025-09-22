package org.example.taller2.service;

import org.example.taller2.entity.Permission;

import java.util.List;

public interface PermissionService {

    Permission createPermission(String name, String description);
    Permission updatePermission(Long id, String newName, String newDescription);
    void deletePermission(Long id);

}
