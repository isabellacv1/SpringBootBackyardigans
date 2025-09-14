package org.example.taller2.service.impl;

import org.example.taller2.entity.User;
import org.example.taller2.entity.UserRole;
import org.example.taller2.repository.RoleRepository;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.taller2.entity.Role;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void createUser(String name, String email, String password, String role){

        Role userRole = roleRepository.findByName(role);
        
        if(userRole == null){
            throw new IllegalArgumentException("Role not found");
        }

        User user = new User(name,email,password);

        UserRole userRoleObj = new

        user.getUserRoles().add(userRole);
        
        userRepository.save(user);
        



    }

}
