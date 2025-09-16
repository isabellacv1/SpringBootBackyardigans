package org.example.taller2.controller;

import org.example.taller2.entity.Permission;
import org.example.taller2.repository.PermissionRepository;
import org.example.taller2.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionRepository permissionRepository;

    @GetMapping("permissions/create")
    public String createPermission() {
        Permission permission = permissionService.createPermission("READ_USERS", "Can read users");
        return "Permiso creado: " + permission.getName();
    }

    @GetMapping("permissions/delete")
    public String deletePermission() {
        List<Permission> permissions = permissionRepository.findAll();
        if (!permissions.isEmpty()) {
            Permission first = permissions.get(0);
            permissionService.deletePermission(first.getId());
            return "Primer permiso eliminado: " + first.getName();
        }
        return "No hay permisos para eliminar";
    }

    @GetMapping("permissions/update")
    public String updatePermission() {
        List<Permission> permissions = permissionRepository.findAll();
        if (permissions.isEmpty()) {
            return "No hay permisos para actualizar";
        }

        Permission first = permissions.get(0);
        String newName = first.getName() + "_UPD";
        String newDesc = (first.getDescription() == null ? "" : first.getDescription()) + " (actualizado)";

        Permission updated = permissionService.updatePermission(first.getId(), newName, newDesc);

        return "Permiso actualizado: " + updated.getId() + " - " + updated.getName();
    }
}