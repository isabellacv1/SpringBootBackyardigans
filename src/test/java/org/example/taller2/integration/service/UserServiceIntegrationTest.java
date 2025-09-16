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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Role role;

    @BeforeEach
    void setup() {
        // Arrange
        role = new Role();
        role.setName("user");
        role.setDescription("Rol de usuario normal");
        roleRepository.save(role);
    }

    @Test
    void createUser_WhenCalled_ShouldPersistUserAndRole() {
        // Arrange
        String name = "Isabella";
        String email = "isa@example.com";
        String password = "1234";

        // Act
        userService.createUser(name, email, password, "ADMIN");

        // Assert
        User savedUser = userRepository.findByName("Isabella");
        assertNotNull(savedUser);
        assertEquals("Isabella", savedUser.getName());
        assertEquals("isa@example.com", savedUser.getEmail());
        assertFalse(savedUser.getUserRoles().isEmpty(), "El usuario debería tener al menos un rol asignado");

    }

    @Test
    void createUser_WithNonExistentRole_ShouldThrowException() {
        // Arrange
        String name = "Andres";
        String email = "andres@example.com";
        String password = "1234";

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(name, email, password, "no-existe");
        });
    }

    @Test
    void deleteUser_WhenCalled_ShouldRemoveUser() {
        // Arrange
        User user = new User();
        user.setName("Carlos");
        user.setEmail("carlos@example.com");
        user.setPassword("1234");
        userRepository.save(user);

        // Act
        userRepository.delete(userRepository.findByName("Carlos"));

        // Assert
        assertNull(userRepository.findByName("Carlos"));
    }

    @AfterEach
    void cleanup() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }
}