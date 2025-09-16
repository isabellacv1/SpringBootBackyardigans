package org.example.taller2.controller;

import org.example.taller2.entity.User;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("users/create")
    public String createUser() {
        userService.createUser("Carlos", "carlos@mail.com", "1234", "ADMIN");
        return "Usuario creado";
    }

    @GetMapping("users")
    public String getUsers() {
        StringBuilder sb = new StringBuilder();
        List<User> users = userRepository.findAll();
        for (User u : users) {
            sb.append(u.getName()).append(" - ").append(u.getEmail()).append("\n");
        }
        return sb.toString();
    }

    @GetMapping("users/delete")
    public String deleteUser() {
        User user = userRepository.findByName("Carlos");
        if (user != null) {
            userService.deleteUser("Carlos");
            return "Usuario Carlos eliminado";
        }
        return "Usuario no encontrado";
    }

    @GetMapping("users/update")
    public String updateUser() {
        User user = userRepository.findByName("Carlos");
        if (user == null) {
            return "Usuario Carlos no existe para actualizar";
        }

        String newEmail = "carlos.updated@mail.com";
        String newPassword = "nuevoPass";
        String newRole = "admin";

        userService.updateUser(user.getName(), newEmail, newPassword, newRole);

        return "Usuario actualizado: " + user.getName();
    }
}