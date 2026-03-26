package com.openclaw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.openclaw.dto.LoginRequest;
import com.openclaw.dto.RegisterRequest;
import com.openclaw.dto.UserDTO;
import com.openclaw.entity.User;
import com.openclaw.exception.BusinessException;
import com.openclaw.mapper.UserMapper;
import com.openclaw.service.UserService;
import com.openclaw.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserDTO register(RegisterRequest request) {
        // 检查用户名是否已存在
        if (userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, request.getUsername())) > 0) {
            throw new BusinessException("用户名已存在");
        }
        // 检查邮箱是否已存在
        if (userMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getEmail, request.getEmail())) > 0) {
            throw new BusinessException("邮箱已被注册");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userMapper.insert(user);

        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user.getId(), user.getUsername());
        long expiresAt = System.currentTimeMillis() + jwtUtil.getTokenExpiration();
        
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .token(token)
                .refreshToken(refreshToken)
                .expiresAt(expiresAt)
                .build();
    }

    @Override
    public UserDTO login(LoginRequest request) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, request.getUsername()));
        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }

        String token = jwtUtil.generateToken(user.getId(), user.getUsername());
        String refreshToken = jwtUtil.generateRefreshToken(user.getId(), user.getUsername());
        long expiresAt = System.currentTimeMillis() + jwtUtil.getTokenExpiration();
        
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .token(token)
                .refreshToken(refreshToken)
                .expiresAt(expiresAt)
                .build();
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .build();
    }
}
