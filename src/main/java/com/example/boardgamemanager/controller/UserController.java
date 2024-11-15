package com.example.boardgamemanager.controller;

import com.example.boardgamemanager.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final PasswordEncoder passwordEncoder;

    public UserController() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // 显示注册表单
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    // 处理注册请求
    @PostMapping("/register")
    public String registerUser(User user) {
        // 对密码进行加密后保存
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 这里模拟保存到数据库（实际使用时可以保存到数据库）
        System.out.println("User registered: " + user.getUsername() + " with password: " + user.getPassword());
        return "redirect:/login";
    }

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
