package com.example.boardgamemanager.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    // 提取当前用户名
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
            !"anonymousUser".equals(authentication.getPrincipal())) {
            return authentication.getName();
        }
        return "guest";
    }
    
 // 获取当前用户权限
    private String getCurrentAuthority() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
            !"anonymousUser".equals(authentication.getPrincipal())) {
            // 提取用户的角色
            return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER"); // 默认返回 ROLE_USER
        }
        return "ROLE_GUEST"; // 未登录用户返回“访客角色”
    }

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        String username = getCurrentUsername();
        String authority = getCurrentAuthority();
        model.addAttribute("username", username);
        model.addAttribute("authority", authority);
    }
}
