package com.openclaw.mapper;

import com.openclaw.entity.UserProfile;
import org.apache.ibatis.annotations.*;
import java.util.Optional;

@Mapper
public interface UserProfileMapper {
    
    @Insert("INSERT INTO user_profile (user_id, nickname, avatar_url, bio, birthday, gender) VALUES (#{userId}, #{nickname}, #{avatarUrl}, #{bio}, #{birthday}, #{gender})")
    int insert(UserProfile userProfile);
    
    @Select("SELECT * FROM user_profile WHERE id = #{id}")
    UserProfile selectById(Long id);
    
    @Select("SELECT * FROM user_profile WHERE user_id = #{userId}")
    UserProfile selectByUserId(Long userId);
    
    @Update("UPDATE user_profile SET nickname = #{nickname}, avatar_url = #{avatarUrl}, bio = #{bio}, birthday = #{birthday}, gender = #{gender}, updated_at = CURRENT_TIMESTAMP WHERE user_id = #{userId}")
    int updateByUserId(UserProfile userProfile);
    
    @Delete("DELETE FROM user_profile WHERE id = #{id}")
    int deleteById(Long id);
    
    @Delete("DELETE FROM user_profile WHERE user_id = #{userId}")
    int deleteByUserId(Long userId);
}