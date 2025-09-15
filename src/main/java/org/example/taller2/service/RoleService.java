package org.example.taller2.service;

import org.example.taller2.entity.Role;

import java.util.List;

public interface RoleService {

    void createRole(String name, String description, String permission);
    void deleteRole(Long roleId);
    void updateRole(Long roleId, String name, String description, String permission);

}
