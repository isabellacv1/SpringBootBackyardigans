package org.example.taller2.service;

import org.example.taller2.dto.FlashMessage;
import org.example.taller2.entity.User;

public interface UserService {
    FlashMessage createUser(User user);
    void deleteUser(String userName);
    FlashMessage updateUser(User user);

    User findByUsername(String username);
}
