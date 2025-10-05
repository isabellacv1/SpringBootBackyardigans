package org.example.taller2.service.impl;

import org.example.taller2.dto.FlashMessage;
import org.example.taller2.dto.FlashMessageType;
import org.example.taller2.dto.UserUpdateDTO;
import org.example.taller2.entity.Role;
import org.example.taller2.entity.User;
import org.example.taller2.entity.UserRole;
import org.example.taller2.entity.UserRoleId;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.repository.UserRoleRepository;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public FlashMessage createUser(User user) {
        FlashMessage flashMessage;
        Optional<User> userAlreadyExist = userRepository.findByEmail(user.getEmail());
        if (userAlreadyExist.isPresent()) {
                 flashMessage = new FlashMessage(FlashMessageType.WARNING, "El email de usuario ya existe");
        }
        else {
            flashMessage = new FlashMessage(FlashMessageType.SUCCESS, "Usted se ha registrado correctamente dentro del sistema");
            String bcryptPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(bcryptPassword);
            userRepository.save(user);
        }

        return flashMessage;
    }

    @Override
    public FlashMessage deleteUser(String userName) {
        User user = userRepository.findByEmail(userName).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(user);
        return new FlashMessage(FlashMessageType.SUCCESS, "El usuario fue eliminado exitosamente.");

    }

    @Override
    public FlashMessage updateUser(String username, UserUpdateDTO dto) {
        if (username == null || username.isEmpty()) {
            return new FlashMessage(FlashMessageType.ERROR, "El nombre del usuario es obligatorio");
        }

        Optional<User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty()) {
            return new FlashMessage(FlashMessageType.ERROR, "El usuario " + username + " no existe en la base de datos");
        }

        User user = optionalUser.get();

        if (dto.getNewEmail() != null && !dto.getNewEmail().isEmpty()) {
            user.setEmail(dto.getNewEmail());
        }

        if (dto.getNewPassword() != null && !dto.getNewPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        }

        userRepository.save(user);

        return new FlashMessage(FlashMessageType.SUCCESS, "Usuario " + user.getName() + " actualizado correctamente");
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public FlashMessage addRolesToUser(Long userId, List<Long> roleIds) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new FlashMessage(FlashMessageType.ERROR, "Usuario no encontrado");
        }

        for (Long roleId : roleIds) {
            Role role = roleRepository.findById(roleId).orElse(null);
            if (role != null) {
                boolean alreadyHasRole = user.getUserRoles().stream()
                        .anyMatch(ur -> ur.getRole().getId().equals(roleId));
                if (!alreadyHasRole) {
                    UserRole userRole = new UserRole();
                    userRole.setUserRoleId(new UserRoleId(userId, roleId));
                    userRole.setUser(user);
                    userRole.setRole(role);
                    userRoleRepository.save(userRole);
                }
            }
        }
        return new FlashMessage(FlashMessageType.SUCCESS, "Roles asignados correctamente");
    }
}
