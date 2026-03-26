package com.baozipu.service.impl;
import com.baozipu.entity.dto.UserDto;
import com.baozipu.service.UserService;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {
    public Map<String, Object> login(UserDto userDto) {
        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", UUID.randomUUID().toString());
        result.put("refreshToken", UUID.randomUUID().toString());
        result.put("expiresIn", 7200);
        return result;
    }
    public boolean register(UserDto userDto) { return true; }
    public Map<String, Object> refreshToken(String refreshToken) {
        Map<String, Object> result = new HashMap<>();
        result.put("accessToken", UUID.randomUUID().toString());
        return result;
    }
}