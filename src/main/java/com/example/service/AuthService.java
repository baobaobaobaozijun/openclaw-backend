package com.example.service;

import com.example.entity.User;
import com.example.dto.UserProfileUpdateRequest;

public interface AuthService {
    User getUserProfile(Long userId);
    
    User updateProfile(Long userId, UserProfileUpdateRequest request);
}