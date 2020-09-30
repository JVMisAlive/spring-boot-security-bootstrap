package com.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.web.model.User;
import com.web.service.RoleService;
import com.web.service.UserService;


@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String userInfo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userRoles = roleService.getViewRoleSet(user);
        model.addAttribute("userData", user);
        model.addAttribute("userRoles", userRoles);
        return "user";
    }


}
