package org.example.taller2.integration.service;

import org.example.taller2.entity.Role;
import org.example.taller2.entity.User;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.service.UserService;
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
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Role basicRole;

    @BeforeEach
    void setup() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        basicRole = new Role("Rol básico", "USER");
        basicRole = roleRepository.save(basicRole);
    }

    @Test
    @Transactional
    void createUser_WhenCalled_ShouldSaveUser() {
        // Act
        userService.createUser("juanperez", "juan@example.com", "1234", "USER");

        // Assert
        User found = userRepository.findByName("juanperez");
        assertNotNull(found);
        assertEquals("juan@example.com", found.getEmail());
        assertEquals("1234", found.getPassword());
        assertFalse(found.getUserRoles().isEmpty());
        assertEquals("USER", found.getUserRoles().iterator().next().getRole().getName());
    }

    @Test
    @Transactional
    void updateUser_WhenCalled_ShouldUpdateUserAndRole() {
        // Arrange
        userService.createUser("mariagomez", "maria@example.com", "pass", "USER");
        Role adminRole = new Role("Administrador", "ADMIN");
        roleRepository.save(adminRole);

        // Act
        userService.updateUser("mariagomez", "mariaupdated@example.com", "newpass", "ADMIN");

        // Assert
        User found = userRepository.findByName("mariagomez");
        assertNotNull(found);
        assertEquals("mariaupdated@example.com", found.getEmail());
        assertEquals("newpass", found.getPassword());
        assertEquals("ADMIN", found.getUserRoles().iterator().next().getRole().getName());
    }

    @Test
    void deleteUser_WhenCalled_ShouldRemoveUser() {
        // Arrange
        userService.createUser("carlossoto", "carlos@example.com", "mypassword", "USER");

        // Act
        userService.deleteUser("carlossoto");

        // Assert
        User found = userRepository.findByName("carlossoto");
        assertNull(found);
    }

    @Test
    void createUser_WhenRoleNotFound_ShouldThrowException() {
        // Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                userService.createUser("pepito", "pepito@example.com", "1234", "NO_EXISTE"));
        assertEquals("Role not found", exception.getMessage());
    }

    @Test
    void updateUser_WhenUserNotFound_ShouldThrowException() {
        // Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                userService.updateUser("noexiste", "email", "pass", "USER"));
        assertEquals("User not found", exception.getMessage());
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }
}