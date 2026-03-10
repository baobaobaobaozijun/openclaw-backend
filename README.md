# OpenClaw Backend

OpenClaw 后端服务 - 基于 Spring Boot 3.2 + Java 21

## 技术栈

- **Java**: 21 (LTS)
- **Spring Boot**: 3.2.3
- **MySQL**: 8.0+
- **Redis**: 7.0+
- **MyBatis-Plus**: 3.5.5
- **JJWT**: 0.11.5

## 项目结构

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/openclaw/
│   │   │   ├── auth/          # 认证模块
│   │   │   ├── user/          # 用户模块
│   │   │   ├── config/        # 配置类
│   │   │   ├── exception/     # 异常处理
│   │   │   └── common/        # 公共组件
│   │   └── resources/
│   │       ├── application.yml
│   │       └── mapper/
│   └── test/
│       └── java/com/openclaw/
└── pom.xml
```

## 快速开始

### 环境要求

- JDK 21+
- Maven 3.9+
- MySQL 8.0+
- Redis 7.0+

### 构建项目

```bash
mvn clean install
```

### 运行项目

```bash
mvn spring-boot:run
```

### 配置说明

复制 `application.yml` 为 `application-local.yml` 并修改以下配置：

```yaml
spring:
  datasource:
    url: jdbc:mysql://your-host:3306/openclaw
    username: your-username
    password: your-password
  data:
    redis:
      host: your-redis-host
      password: your-redis-password

jwt:
  secret: your-secret-key
```

## 开发规范

参考 [SOUL.md](../../SOUL.md) 中的行为准则：

- ✅ 质量优先 - 绝不提交未经测试的代码
- ✅ 测试驱动 - 先写测试，再实现功能
- ✅ 文档先行 - 写代码前先写 API 文档
- ✅ 持续优化 - 发现坏味道立即重构

## 模块说明

### 用户认证模块 (auth)

- 用户登录
- 用户注册
- JWT Token 管理
- 密码重置

### 用户模块 (user)

- 用户信息管理
- 用户角色管理

## 测试

```bash
# 运行所有测试
mvn test

# 运行特定测试类
mvn test -Dtest=AuthServiceTest

# 生成测试覆盖率报告
mvn clean test jacoco:report
```

## API 文档

启动项目后访问：`http://localhost:8080/api/swagger-ui.html`

## 协作

- **PM**: 灌汤
- **前端**: 豆沙
- **测试**: 酸菜

## 许可证

MIT License

---

**维护者**: 酱肉 (Jiangrou)  
**最后更新**: 2026-03-10
