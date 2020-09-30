package com.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.web.model.Role;
import com.web.model.User;
import com.web.service.RoleService;
import com.web.service.UserService;

import javax.validation.Valid;
import java.util.Collections;


@Controller
public class RegistrationController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String login(){
        return "login";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(Model model) {

        model.addAttribute("userForm", new User());
        model.addAttribute("userRoles", roleService.getAllRoles());
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        userForm.setRoles(Collections.singleton(new Role(1L, "USER")));
        userService.saveUser(userForm);
        return "redirect:/login";
    }

}