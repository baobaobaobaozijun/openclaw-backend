package com.baozipu.controller;

import com.baozipu.common.Result;
import com.baozipu.entity.dto.UserDto;
import com.baozipu.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "认证接口", description = "用户登录、注册、刷新令牌等认证相关接口")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "用户登录", description = "用户登录获取访问令牌")
    @PostMapping("/login")
    public Result login(@RequestBody UserDto userDto) {
        try {
            Map<String, Object> result = userService.login(userDto);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("登录失败: " + e.getMessage());
        }
    }

    @Operation(summary = "用户注册", description = "用户注册新账号")
    @PostMapping("/register")
    public Result register(@RequestBody UserDto userDto) {
        try {
            boolean success = userService.register(userDto);
            if (success) {
                return Result.success("注册成功");
            } else {
                return Result.error("注册失败");
            }
        } catch (Exception e) {
            return Result.error("注册失败: " + e.getMessage());
        }
    }

    @Operation(summary = "刷新令牌", description = "使用刷新令牌获取新的访问令牌")
    @PostMapping("/refresh")
    public Result refresh(@RequestBody Map<String, String> refreshRequest) {
        try {
            String refreshToken = refreshRequest.get("refreshToken");
            Map<String, Object> result = userService.refreshToken(refreshToken);
            return Result.success(result);
        } catch (Exception e) {
            return Result.error("刷新令牌失败: " + e.getMessage());
        }
    }
}