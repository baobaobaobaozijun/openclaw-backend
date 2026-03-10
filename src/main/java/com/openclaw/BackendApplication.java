package com.openclaw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * OpenClaw 后端服务启动类
 * 
 * @author 酱肉 (Jiangrou)
 * @since 2026-03-10
 */
@SpringBootApplication
@MapperScan("com.openclaw.**.mapper")
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
