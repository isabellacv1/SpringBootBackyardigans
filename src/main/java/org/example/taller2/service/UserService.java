package org.example.taller2.service;

import org.example.taller2.dto.FlashMessage;
import org.example.taller2.dto.UserUpdateDTO;
import org.example.taller2.entity.User;

import java.util.List;

public interface UserService {
    FlashMessage createUser(User user);
    FlashMessage deleteUser(String userName);
    FlashMessage updateUser(String username, UserUpdateDTO dto);

    User findByEmail(String username);

    User findById(Long id);

    List<User> findAll();

    FlashMessage addRolesToUser(Long userId, List<Long> roleIds);
}
