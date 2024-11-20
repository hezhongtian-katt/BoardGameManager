package com.example.boardgamemanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    private final DataSource dataSource;

    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 配置 HTTP 安全
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() 
            .authorizeRequests()
                .requestMatchers("/login", "/signup").permitAll()  // 允许未登录的请求
                .anyRequest().authenticated()  // 其他请求需要认证
            .and()
            .formLogin()
                .loginPage("/login")  // 自定义登录页面
                .permitAll()
            .and()
            .logout()
	            .logoutUrl("/logout")  // 指定登出 URL
	            .logoutSuccessUrl("/login?logout")  // 登出后跳转到登录页面
	            .permitAll();  // 允许所有人访问登出

        return http.build();
    }

    // 配置 AuthenticationManager 来验证用户名和密码
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
                http.getSharedObject(AuthenticationManagerBuilder.class); // 获取 builder
        authenticationManagerBuilder
            .jdbcAuthentication()
                .dataSource(dataSource)  // 使用数据库进行身份验证
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?")
                .passwordEncoder(passwordEncoder());  // 使用密码编码器

        return authenticationManagerBuilder.build();  // 返回配置好的 AuthenticationManager
    }

    // 配置密码加密器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
