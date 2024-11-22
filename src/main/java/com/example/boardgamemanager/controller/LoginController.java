package com.example.boardgamemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // 返回 login.html 页面
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        // 登录验证逻辑通过 Spring Security 处理
        return "redirect:/";  // 登录成功后重定向到主页
    }
}
