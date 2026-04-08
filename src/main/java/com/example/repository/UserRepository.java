package com.example.repository;

import com.example.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseMapper<User> {
}