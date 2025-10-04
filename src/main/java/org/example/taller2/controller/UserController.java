package org.example.taller2.controller;

import org.example.taller2.dto.FlashMessage;
import org.example.taller2.entity.User;
import org.example.taller2.repository.UserRepository;
import org.example.taller2.security.CustomUserDetails;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
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

    @PostMapping("/update")
    public String update(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        FlashMessage message = userService.updateUser(user);
        redirectAttributes.addFlashAttribute("message", message);
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


}