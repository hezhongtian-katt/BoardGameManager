package com.example.boardgamemanager.service;

import com.example.boardgamemanager.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

@Service
public class UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void registerUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        String sql = "INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getUsername(), encodedPassword, true);

        String authoritySql = "INSERT INTO authorities (username, authority) VALUES (?, ?)";
        jdbcTemplate.update(authoritySql, user.getUsername(), "ROLE_USER");  // 默认角色
    }

    // 其他用户操作...
}
