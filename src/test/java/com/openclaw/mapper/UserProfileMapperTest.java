package com.openclaw.mapper;

import com.openclaw.entity.UserProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

@SpringBootTest
public class UserProfileMapperTest {

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Test
    public void testInsert() {
        UserProfile profile = new UserProfile();
        profile.setUserId(1L);
        profile.setNickname("测试用户");
        profile.setAvatarUrl("https://example.com/avatar.jpg");
        profile.setBio("这是一个测试用户");
        profile.setBirthday(LocalDate.of(1990, 1, 1));
        profile.setGender((byte) 1);

        int result = userProfileMapper.insert(profile);
        assertEquals(1, result, "插入应该成功");
    }

    @Test
    public void testSelectByUserId() {
        UserProfile profile = userProfileMapper.selectByUserId(1L);
        assertNotNull(profile, "应该能找到用户资料");
        assertEquals("测试用户", profile.getNickname());
    }

    @Test
    public void testUpdateByUserId() {
        UserProfile profile = new UserProfile();
        profile.setUserId(1L);
        profile.setNickname("更新后的昵称");
        profile.setBio("更新后的简介");

        int result = userProfileMapper.updateByUserId(profile);
        assertEquals(1, result, "更新应该成功");

        UserProfile updated = userProfileMapper.selectByUserId(1L);
        assertEquals("更新后的昵称", updated.getNickname());
        assertEquals("更新后的简介", updated.getBio());
    }

    @Test
    public void testDeleteByUserId() {
        int result = userProfileMapper.deleteByUserId(1L);
        assertEquals(1, result, "删除应该成功");

        UserProfile deleted = userProfileMapper.selectByUserId(1L);
        assertNull(deleted, "用户资料应该已被删除");
    }
}
