package org.example.taller2.integration.service;

import org.example.taller2.entity.Permission;
import org.example.taller2.entity.Role;
import org.example.taller2.repository.PermissionRepository;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.service.RoleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class RoleServiceIntegrationTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    private Permission permission;

    @BeforeEach
    void setup() {
        roleRepository.deleteAll();
        permissionRepository.deleteAll();

        permission = new Permission();
        permission.setName("READ_USER");
        permission.setDescription("Permite leer usuarios");
        permission = permissionRepository.save(permission);
    }

    @Test
    @Transactional
    void createRole_WhenCalled_ShouldSaveRoleWithPermission() {
        // Act
        roleService.createRole("ADMIN", "Administrador del sistema", permission.getName());

        // Assert
        Role foundRole = roleRepository.findByName("ADMIN");
        assertNotNull(foundRole);
        assertEquals("Administrador del sistema", foundRole.getDescription());
        assertFalse(foundRole.getRolePermissions().isEmpty());
        assertEquals(permission.getName(),
                foundRole.getRolePermissions().iterator().next().getPermission().getName());
    }

    @Test
    @Transactional
    void updateRole_WhenCalled_ShouldUpdateRoleFieldsAndPermissions() {
        // Arrange
        roleService.createRole("USER", "Usuario básico", permission.getName());
        Role role = roleRepository.findByName("USER");

        Permission newPermission = new Permission();
        newPermission.setName("WRITE_USER");
        newPermission.setDescription("Permite escribir usuarios");
        newPermission = permissionRepository.save(newPermission);

        // Act
        roleService.updateRole(role.getId(), "UserUpdated", "Usuario avanzado", newPermission.getName());

        // Assert
        Role updatedRole = roleRepository.findByName("UserUpdated");
        assertNotNull(updatedRole);
        assertEquals("Usuario avanzado", updatedRole.getDescription());
        assertEquals(1, updatedRole.getRolePermissions().size());
        assertEquals(newPermission.getName(),
                updatedRole.getRolePermissions().iterator().next().getPermission().getName());
    }

    @Test
    @Transactional
    void deleteRole_WhenCalled_ShouldRemoveRole() {
        // Arrange
        roleService.createRole("TempRole", "Rol temporal", permission.getName());
        Role role = roleRepository.findByName("TempRole");
        assertNotNull(role);

        // Act
        roleService.deleteRole(role.getId());

        // Assert
        Optional<Role> found = roleRepository.findById(role.getId());
        assertFalse(found.isPresent());
    }

    @AfterEach
    void cleanup() {
        roleRepository.deleteAll();
        permissionRepository.deleteAll();
    }
}
