package com.openclaw.service;

import com.openclaw.dto.LoginRequest;
import com.openclaw.dto.RegisterRequest;
import com.openclaw.dto.UserDTO;

public interface UserService {
    UserDTO register(RegisterRequest request);
    UserDTO login(LoginRequest request);
    UserDTO getUserById(Long id);
}