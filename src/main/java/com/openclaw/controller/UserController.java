package com.openclaw.controller;

import com.openclaw.common.Result;
import com.openclaw.entity.UserProfile;
import com.openclaw.mapper.UserProfileMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "用户管理", description = "用户资料管理")
public class UserController {

    private final UserProfileMapper userProfileMapper;

    /**
     * 获取用户资料
     */
    @GetMapping("/profile/{userId}")
    @Operation(summary = "获取用户资料", description = "根据用户 ID 获取用户资料信息")
    public Result<UserProfile> getUserProfile(@PathVariable Long userId) {
        UserProfile profile = userProfileMapper.selectByUserId(userId);
        if (profile == null) {
            return Result.error("用户资料不存在");
        }
        return Result.success(profile);
    }

    /**
     * 更新用户资料
     */
    @PutMapping("/profile")
    @Operation(summary = "更新用户资料", description = "更新用户昵称、头像、简介等信息")
    public Result<String> updateUserProfile(@RequestBody UserProfile userProfile) {
        if (userProfile.getUserId() == null) {
            return Result.error("用户 ID 不能为空");
        }
        
        // 检查用户资料是否存在
        UserProfile existing = userProfileMapper.selectByUserId(userProfile.getUserId());
        if (existing == null) {
            // 如果不存在，创建新的用户资料
            userProfileMapper.insert(userProfile);
        } else {
            // 如果存在，更新用户资料
            userProfileMapper.updateByUserId(userProfile);
        }
        
        return Result.success("用户资料更新成功");
    }
}
