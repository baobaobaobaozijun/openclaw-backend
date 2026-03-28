package com.baozipu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // 这里假设有一个 UserService 来处理用户相关的业务逻辑
    // 实际使用时需要注入相应的 Service 并实现相关方法

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        // 这里应该调用 UserService 验证用户名和密码
        // 为了演示，这里只是模拟返回值
        boolean isValid = validateUser(username, password);

        if (isValid) {
            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            
            response.put("code", 200);
            data.put("id", 1);
            data.put("username", username);
            data.put("token", "jwt_token"); // 实际应用中应该是生成的 JWT token
            response.put("data", data);
            
            return response;
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 500);
            response.put("message", "用户名或密码错误");
            return response;
        }
    }

    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String email = request.get("email");

        // 这里应该调用 UserService 检查用户是否存在以及注册用户
        // 为了演示，这里只是模拟返回值
        boolean userExists = checkUserExists(username);

        if (userExists) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", 500);
            response.put("message", "用户名已存在");
            return response;
        } else {
            // 实际应用中应该加密密码并保存用户信息
            String encodedPassword = passwordEncoder.encode(password);
            Long userId = saveUser(username, encodedPassword, email);

            Map<String, Object> response = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            
            response.put("code", 200);
            data.put("id", userId);
            data.put("username", username);
            response.put("data", data);
            
            return response;
        }
    }

    // 模拟验证用户的方法
    private boolean validateUser(String username, String password) {
        // 实际应用中应该从数据库获取用户信息并验证密码
        // 这里仅为演示返回 true
        return true;
    }

    // 模拟检查用户是否存在的方法
    private boolean checkUserExists(String username) {
        // 实际应用中应该查询数据库
        // 这里仅为演示返回 false
        return false;
    }

    // 模拟保存用户的方法
    private Long saveUser(String username, String encodedPassword, String email) {
        // 实际应用中应该将用户信息保存到数据库
        // 这里仅为演示返回 1L
        return 1L;
    }
}