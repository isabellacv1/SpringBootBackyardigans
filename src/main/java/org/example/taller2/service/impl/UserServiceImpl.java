package org.example.taller2.service.impl;

import org.example.taller2.entity.User;
import org.example.taller2.entity.UserRole;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.taller2.entity.Role;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public void createUser(String name, String email, String password, String role) {

        Role userRole = roleRepository.findByName(role);

        if (userRole == null) {
            throw new IllegalArgumentException("Role not found");
        }


        User user = new User(name, email, password);
        user = userRepository.save(user);


        UserRole userRoleObj = new UserRole();
        userRoleObj.setUser(user);
        userRoleObj.setRole(userRole);

        user.getUserRoles().add(userRoleObj);

        userRepository.save(user);
    }

    @Override
    public void deleteUser(String userName) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        userRepository.delete(user);
    }

    @Override
    public void updateUser(String userName, String newEmail, String newPassword, String newRole) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (newEmail != null && !newEmail.isEmpty()) {
            user.setEmail(newEmail);
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(newPassword);
        }

        if (newRole != null && !newRole.isEmpty()) {
            Role roleEntity = roleRepository.findByName(newRole);
            if (roleEntity == null) {
                throw new IllegalArgumentException("Role not found");
            }

            user.getUserRoles().clear();

            UserRole userRoleObj = new UserRole();
            userRoleObj.setUser(user);
            userRoleObj.setRole(roleEntity);
            user.getUserRoles().add(userRoleObj);
        }

        userRepository.save(user);
    }
}


