package org.example.taller2.controller;

import org.example.taller2.entity.Role;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("roles/create")
    public String createRole() {
        Role existingRole = roleRepository.findByName("ADMIN");
        if (existingRole != null) {
            return "El rol ya existe: " + existingRole.getName();
        }

        roleService.createRole("ADMIN", "Administrador del sistema", "READ_USERS");
        return "Rol creado: ADMIN";
    }

    @GetMapping("roles/delete")
    public String deleteRole() {
        List<Role> roles = roleRepository.findAll();
        if (!roles.isEmpty()) {
            Role first = roles.get(0);
            try {
                roleService.deleteRole(first.getId());
                return "Primer rol eliminado: " + first.getName();
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        }
        return "No hay roles para eliminar";
    }

    @GetMapping("roles/update")
    public String updateRole() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            return "No hay roles para actualizar";
        }

        Role first = roles.get(0);
        String newName = first.getName() + "_UPD";
        String newDesc = (first.getDescription() == null ? "" : first.getDescription()) + " (actualizado)";

        try {
            roleService.updateRole(first.getId(), newName, newDesc, "READ_USERS");
            return "Rol actualizado: " + first.getId() + " - " + newName;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}