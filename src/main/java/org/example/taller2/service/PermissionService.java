package org.example.taller2.service;

import org.example.taller2.entity.Permission;

import java.util.List;

public interface PermissionService {

    void createPermission(String name, String description);

    List<Permission> getPermissions();
}
