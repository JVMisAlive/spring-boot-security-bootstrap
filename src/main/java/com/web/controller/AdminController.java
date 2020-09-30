package com.web.controller;

import com.web.model.User;
import com.web.service.RoleService;
import com.web.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;


@Controller
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String userList(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRoles = roleService.getViewRoleSet(user);
        model.addAttribute("addUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        model.addAttribute("userList", userService.allUsers());
        model.addAttribute("authUser", user);
        model.addAttribute("userRoles", userRoles);
        return "usertable";
    }

    @PostMapping("/admin/add")
    public ModelAndView createUser(@ModelAttribute("userAdd") User user, @RequestParam("addRole") Set<String> editRole) {
        user.setRoles(roleService.getRoleSet(editRole));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.saveUser(user);
        return modelAndView;
    }

    @PostMapping("/admin/edit/{id}")
    public ModelAndView editUser(@ModelAttribute("userEdit") User user, @RequestParam("editRole") Set<String> editRole) {
        user.setRoles(roleService.getRoleSet(editRole));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.edit(user);
        return modelAndView;
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}