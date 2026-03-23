package com.openclaw.controller;

import com.openclaw.common.Result;
import com.openclaw.dto.LoginRequest;
import com.openclaw.dto.RegisterRequest;
import com.openclaw.dto.UserDTO;
import com.openclaw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public Result<UserDTO> login(@RequestBody LoginRequest request) {
        UserDTO user = userService.login(request);
        return Result.success(user);
    }

    @PostMapping("/register")
    public Result<UserDTO> register(@RequestBody RegisterRequest request) {
        UserDTO user = userService.register(request);
        return Result.success(user);
    }
}
