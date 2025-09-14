package org.example.taller2.service;

import org.example.taller2.entity.Role;

import java.util.List;

public interface RoleService {

    void createRole(String name, String description);

    List<Role> getRoles();
}
