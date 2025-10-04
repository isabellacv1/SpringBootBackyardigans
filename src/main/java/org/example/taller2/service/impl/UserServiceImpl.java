package org.example.taller2.service.impl;

import org.example.taller2.dto.FlashMessage;
import org.example.taller2.dto.FlashMessageType;
import org.example.taller2.entity.Role;
import org.example.taller2.entity.User;
import org.example.taller2.entity.UserRole;
import org.example.taller2.entity.UserRoleId;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public FlashMessage createUser(User user) {
        FlashMessage flashMessage;
        Optional<User> userAlreadyExist = Optional.ofNullable(userRepository.findByName(user.getName()));
        if (userAlreadyExist.isPresent()) {
                 flashMessage = new FlashMessage(FlashMessageType.WARNING, "El nombre de usuario ya existe");
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
        User user = userRepository.findByName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.delete(user);
        return new FlashMessage(FlashMessageType.SUCCESS, "El usuario fue eliminado exitosamente.");

    }

    @Override
    public FlashMessage updateUser(User user) {
        Optional<User> userAlreadyCreated = Optional.ofNullable(userRepository.findByName(user.getName()));
        FlashMessage flashMessage;
        if (userAlreadyCreated.isEmpty()) {
            flashMessage = new FlashMessage(FlashMessageType.ERROR, "El usuario" + user.getName() + "no existe dentro de la base de datos");
        }else{

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