package org.example.taller2.controller;

import org.example.taller2.dto.FlashMessage;
import org.example.taller2.entity.Role;
import org.example.taller2.entity.User;
import org.example.taller2.service.RoleService;
import org.example.taller2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/index")
    public String adminIndex(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/index";
    }

    @GetMapping("/user/{id}/roles/associate")
    public String showAssociateRoleForm(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        List<Role> allRoles = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        return "admin/associate-role";
    }

    @PostMapping("/user/{id}/roles/associate")
    public String associateRole(@PathVariable Long id, @RequestParam(value = "roleIds", required = false) List<Long> roleIds, RedirectAttributes redirectAttributes) {
        if (roleIds == null || roleIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", new FlashMessage(org.example.taller2.dto.FlashMessageType.ERROR, "Debe seleccionar al menos un rol"));
            return "redirect:/admin/user/" + id + "/roles/associate";
        }
        FlashMessage message = userService.addRolesToUser(id, roleIds);
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/admin/index";
    }
}
