package com.baopu.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * 单元测试基类
 * 所有测试类继承此类，自动获得 Mockito 支持
 */
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public abstract class BaseTest {

    @BeforeEach
    void setUp() {
        // 初始化逻辑，子类可覆盖
    }
}