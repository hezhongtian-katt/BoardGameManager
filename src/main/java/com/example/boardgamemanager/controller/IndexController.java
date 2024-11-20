package com.example.boardgamemanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        // 获取当前用户名
        String username = getCurrentUsername();
        model.addAttribute("username", username);
        return "index";
    }

    // 获取当前用户名的方法
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
            !"anonymousUser".equals(authentication.getPrincipal())) {
            return authentication.getName();
        }
        return "游客"; // 如果未登录，显示“游客”
    }
}
