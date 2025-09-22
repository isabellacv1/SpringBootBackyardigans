package org.example.taller2.service.impl;

import org.example.taller2.entity.Role;
import org.example.taller2.entity.User;
import org.example.taller2.entity.UserRole;
import org.example.taller2.entity.UserRoleId;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void createUser(String name, String email, String password, String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            throw new IllegalArgumentException("Role not found");
        }

        User user = new User(name, email, password);

        UserRole userRole = new UserRole();
        userRole.setUserRoleId(new UserRoleId());
        userRole.setUser(user);
        userRole.setRole(role);

        user.getUserRoles().add(userRole);

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String userName) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void updateUser(String userName, String newEmail, String newPassword, String newRoleName) {
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

        if (newRoleName != null && !newRoleName.isEmpty()) {
            Role role = roleRepository.findByName(newRoleName);
            if (role == null) {
                throw new IllegalArgumentException("Role not found");
            }

            user.getUserRoles().clear();

            UserRole newUserRole = new UserRole();
            newUserRole.setUserRoleId(new UserRoleId());
            newUserRole.setUser(user);
            newUserRole.setRole(role);

            user.getUserRoles().add(newUserRole);
        }
    }
}