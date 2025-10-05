package org.example.taller2.controller;

import org.example.taller2.dto.FlashMessage;
import org.example.taller2.dto.UserUpdateDTO;
import org.example.taller2.entity.User;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.security.CustomUserDetails;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/update")
    public String update(Model model, Authentication auth) {
        CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
        model.addAttribute("user", user);
        return "auth/update";
    }

    @GetMapping("/{username}/edit")
    public String showEditForm(@PathVariable String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("userUpdateDTO", new UserUpdateDTO());
        return "users/edit";
    }

    @PostMapping("/{username}/update")
    public String update(@PathVariable String username, @ModelAttribute UserUpdateDTO userUpdateDTO, Model model) {
        FlashMessage message = userService.updateUser(username, userUpdateDTO);
        model.addAttribute("flashMessage", message);
        model.addAttribute("username", username);
        return "redirect:/user/profile";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("user", new User());
        return "auth/delete";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute User user) {
        userService.deleteUser(user.getName());
        return "redirect:/auth/login";
    }

    @GetMapping("/users")
    public String admin(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/index";
    }


}