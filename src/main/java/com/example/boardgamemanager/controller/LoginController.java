package com.example.boardgamemanager.controller;

import com.example.boardgamemanager.model.User;
import com.example.boardgamemanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // 返回 login.html 页面
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        // 登录验证逻辑通过 Spring Security 处理
        return "redirect:/";  // 登录成功后重定向到主页
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";  // 返回注册页面
    }

    @PostMapping("/signup")
    public String registerUser(User user) {
        userService.registerUser(user);
        return "redirect:/login";  // 注册成功后跳转到登录页面
    }
}
