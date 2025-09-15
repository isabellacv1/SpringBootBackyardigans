package org.example.taller2.integration.service;

import org.example.taller2.entity.Permission;
import org.example.taller2.repository.PermissionRepository;
import org.example.taller2.service.PermissionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PermissionServiceIntegrationTest {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PermissionRepository permissionRepository;

    private Permission permission;

    @BeforeEach
    void setup() {
        permissionRepository.deleteAll();
    }

    @Test
    void createPermission_WhenCalled_ShouldSavePermission() {
        // Act
        Permission created = permissionService.createPermission("READ_USER", "Permite leer usuarios");

        // Assert
        assertNotNull(created.getId());
        Optional<Permission> found = permissionRepository.findById(created.getId());
        assertTrue(found.isPresent());
        assertEquals("READ_USER", found.get().getName());
    }

    @Test
    void updatePermission_WhenCalled_ShouldUpdatePermission() {
        // Arrange
        Permission created = permissionService.createPermission("WRITE_USER", "Permite escribir usuarios");

        // Act
        Permission updated = permissionService.updatePermission(created.getId(), "WRITE_USER_UPDATED", "Descripción actualizada");

        // Assert
        assertEquals("WRITE_USER_UPDATED", updated.getName());
        assertEquals("Descripción actualizada", updated.getDescription());

        Optional<Permission> found = permissionRepository.findById(created.getId());
        assertTrue(found.isPresent());
        assertEquals("WRITE_USER_UPDATED", found.get().getName());
    }

    @Test
    void deletePermission_WhenCalled_ShouldRemovePermission() {
        // Arrange
        Permission created = permissionService.createPermission("DELETE_USER", "Permite borrar usuarios");

        // Act
        permissionService.deletePermission(created.getId());

        // Assert
        Optional<Permission> found = permissionRepository.findById(created.getId());
        assertFalse(found.isPresent());
    }

    @AfterEach
    void cleanup() {
        permissionRepository.deleteAll();
    }
}