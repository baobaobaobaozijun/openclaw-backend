package com.openclaw.controller;

import com.openclaw.dto.UserDTO;
import com.openclaw.service.UserService;
import com.openclaw.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@Tag(name = "用户管理", description = "用户认证与信息管理接口")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/me")
    @Operation(summary = "获取当前用户信息", description = "根据 JWT Token 获取当前登录用户的详细信息")
    @ApiResponse(responseCode = "200", description = "成功返回用户信息")
    public UserDTO getCurrentUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Long userId = jwtUtil.getUserIdFromToken(token);
            
            if (userId != null) {
                return userService.getUserById(userId);
            }
        }
        
        return null;
    }
}