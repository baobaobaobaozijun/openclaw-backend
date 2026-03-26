package com.baozipu.service;
import com.baozipu.entity.dto.UserDto;
import java.util.Map;
public interface UserService {
    Map<String, Object> login(UserDto userDto);
    boolean register(UserDto userDto);
    Map<String, Object> refreshToken(String refreshToken);
}