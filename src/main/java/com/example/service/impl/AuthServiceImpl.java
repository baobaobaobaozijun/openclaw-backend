package com.example.service.impl;

import com.example.service.AuthService;
import com.example.entity.User;
import com.example.dto.UserProfileUpdateRequest;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserProfile(Long userId) {
        return userRepository.selectById(userId);
    }

    @Override
    public User updateProfile(Long userId, UserProfileUpdateRequest request) {
        User user = userRepository.selectById(userId);
        if (user != null) {
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setAvatar(request.getAvatar());
            user.setBio(request.getBio());
            int result = userRepository.updateById(user);
            if (result > 0) {
                return user;
            }
        }
        return null;
    }
}