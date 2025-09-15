package org.example.taller2.service;

public interface UserService {
    void createUser(String name, String email, String password, String role);
    void deleteUser(String userName);
    void updateUser(String userName, String newEmail, String newPassword, String newRole);
}
