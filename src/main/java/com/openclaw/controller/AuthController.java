package com.openclaw.controller;

import com.openclaw.common.Result;
import com.openclaw.dto.LoginRequest;
import com.openclaw.dto.RegisterRequest;
import com.openclaw.dto.UserDTO;
import com.openclaw.service.UserService;
import com.openclaw.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "认证管理", description = "用户注册、登录与身份认证")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "通过用户名和密码登录，返回 JWT Token")
    public Result<UserDTO> login(@RequestBody LoginRequest request) {
        UserDTO user = userService.login(request);
        return Result.success(user);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "注册新用户，需提供用户名、密码和邮箱")
    public Result<UserDTO> register(@RequestBody RegisterRequest request) {
        UserDTO user = userService.register(request);
        return Result.success(user);
    }

    @GetMapping("/me")
    @Operation(summary = "获取当前用户信息", description = "根据 JWT Token 获取当前登录用户信息")
    public Result<UserDTO> getCurrentUser(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return Result.error(401, "未登录");
        }

        String token = authorizationHeader.substring(7);
        try {
            Long userId = jwtUtil.getUserIdFromToken(token);
            if (userId == null) {
                return Result.error(401, "未登录");
            }
            
            UserDTO user = userService.getUserById(userId);
            if (user == null) {
                return Result.error(401, "未登录");
            }
            
            // 创建一个新的UserDTO，将token字段设为null
            UserDTO userWithoutToken = new UserDTO();
            userWithoutToken.setId(user.getId());
            userWithoutToken.setUsername(user.getUsername());
            userWithoutToken.setEmail(user.getEmail());
            userWithoutToken.setAvatar(user.getAvatar());
            userWithoutToken.setToken(null);
            
            return Result.success(userWithoutToken);
        } catch (Exception e) {
            return Result.error(401, "未登录");
        }
    }
}