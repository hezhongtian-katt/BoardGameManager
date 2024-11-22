package com.example.boardgamemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.boardgamemanager.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String viewUsers(Model model) {
        // 获取所有用户
        model.addAttribute("users", userService.getAllUsers());
        return "user_list";  // 返回视图名称
    }
}
