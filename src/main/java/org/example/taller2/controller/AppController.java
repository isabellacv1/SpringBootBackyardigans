package org.example.taller2.controller;

import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    public void createUser(String name, String email, String password, String role){
        userService.createUser(name,email,password,role);

    }

    public void deleteUser(String userName){
        userService.deleteUser(userName);
    }


}
