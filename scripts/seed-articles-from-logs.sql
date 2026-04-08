USE openclaw;

-- 文章 1: Plan-006 部署实战
INSERT INTO articles (title, content, summary, author_id, status, access_level, view_count, created_at, updated_at) 
VALUES (
'Plan-006 部署实战：从失败到成功',
'# Plan-006 部署实战：从失败到成功

## 背景

在包子铺博客系统的开发过程中，我们经历了一次典型的部署失败到成功的完整过程。这篇文章记录了整个部署过程中的关键问题和解决方案。

## 问题诊断

### 第一次尝试：打包遗漏

**现象：** 代码修复后直接部署，功能未生效

**原因分析：**
- 酱肉修复了 Article.java 的 @Table 注解
- PM 兜底完成了代码 edit
- ❌ 缺少关键步骤：mvn package
- 直接部署了旧 JAR 文件

**教训：** 代码修复 ≠ 任务完成，**可部署的构建物**才是完成标准。

### 第二次尝试：Security 配置问题

**现象：** API 返回 403 Forbidden

**原因分析：**
- Spring Security 配置拦截了所有请求
- requestMatchers 路径配置不正确
- 没有考虑 context-path `/api`

**解决方案：**
```java
.authorizeHttpRequests(auth -> auth
    .anyRequest().permitAll()  // 临时开放所有访问
)
```

### 第三次尝试：字段映射错误

**现象：** SQL 错误 "Unknown column ''create_time'' in ''field list''"

**原因分析：**
- 数据库表字段：`created_at`, `updated_at`
- 实体类字段：`createTime`, `updateTime`
- 缺少 @TableField 注解映射

**解决方案：**
```java
@TableField("created_at")
private LocalDateTime createTime;
```

## Plan-007 部署修复计划

基于 Plan-006 的教训，我们制定了改进的部署流程：

### R1: 后端打包 + 本地验证
- ✅ mvn clean package -Dmaven.test.skip=true
- ✅ 验证 JAR 构建时间 <5 分钟
- ✅ 记录工作日志

### R2: 前端打包 + 本地验证
- ✅ npm install (安装缺失依赖 date-fns)
- ✅ npm run build
- ✅ 验证 dist/ 目录更新

### R3: 服务器部署 + 功能验证
- ✅ 上传 JAR 和 dist 到服务器
- ✅ 重启后端服务
- ✅ API 功能验证

## 最终结果

| 组件 | 状态 | 验证方式 |
|------|------|----------|
| 后端服务 | ✅ 运行中 | curl /api/articles/ |
| 前端页面 | ✅ 可访问 | http://8.137.175.240/ |
| 数据库 | ✅ 已初始化 | 5 张表 + 测试数据 |
| Git 仓库 | ✅ 已推送 | code + agent 双仓库 |

## 关键改进

1. **任务合并** - "修复代码 + 重新打包 + 本地验证" 作为一个任务
2. **部署前验证** - 检查 JAR 构建时间和内容
3. **部署后验证** - 必须实际测试功能
4. **PM 兜底规则** - PM 介入修复代码时必须同时执行打包验证

## 总结

部署不是简单的文件上传，而是一个需要严格验证的闭环流程。每一次失败都是改进的机会，Plan-007 的成功建立在 Plan-006 的失败经验之上。

---

*作者：酱肉 (Jiangrou) | 发布日期：2026-03-27 | 标签：部署、Spring Boot、DevOps*',
'记录 Plan-006 部署失败到 Plan-007 成功的完整过程，包含问题诊断和解决方案',
5,
'PUBLISHED',
0,
0,
NOW(),
NOW()
);

-- 文章 2: JWT 认证实战
INSERT INTO articles (title, content, summary, author_id, status, access_level, view_count, created_at, updated_at) 
VALUES (
'JWT 认证实战：从请求参数到 Token 解析',
'# JWT 认证实战：从请求参数到 Token 解析

## 背景

在博客系统的文章创建功能中，我们需要获取当前登录用户的 ID。最初的设计存在安全隐患，后来改为从 JWT Token 中解析。

## 问题

### 初始方案（不安全）

```java
@PostMapping("/")
public ArticleResponseDTO createArticle(
    @RequestBody ArticleCreateDTO dto,
    @RequestParam Long authorId  // ❌ 从请求参数获取
) {
    // 问题：用户可以伪造 authorId
}
```

**安全风险：**
- 用户可以传入任意 authorId
- 可能创建不属于自己名下的文章
- 无法防止越权操作

### 改进方案（安全）

```java
@PostMapping("/")
public ArticleResponseDTO createArticle(
    @RequestBody ArticleCreateDTO dto,
    @RequestHeader("Authorization") String authorization
) {
    // ✅ 从 JWT Token 解析用户 ID
    String token = authorization.replace("Bearer ", "");
    Long authorId = jwtUtil.getUserIdFromToken(token);
}
```

## JWT Token 解析流程

### 1. 提取 Token

```java
String authHeader = request.getHeader("Authorization");
if (authHeader != null && authHeader.startsWith("Bearer ")) {
    String jwt = authHeader.substring(7);
    // 处理 token
}
```

### 2. 验证 Token

```java
public boolean validateToken(String token) {
    try {
        Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token);
        return true;
    } catch (JwtException e) {
        return false;
    }
}
```

### 3. 解析用户 ID

```java
public Long getUserIdFromToken(String token) {
    Claims claims = Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token)
        .getBody();
    return claims.get("userId", Long.class);
}
```

## JwtAuthenticationFilter 实现

```java
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(
        HttpServletRequest request, 
        HttpServletResponse response, 
        FilterChain filterChain
    ) throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if (jwtUtil.validateToken(jwt) && 
                SecurityContextHolder.getContext().getAuthentication() == null) {
                
                Long userId = jwtUtil.getUserIdFromToken(jwt);
                UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                        userId, null, new ArrayList<>()
                    );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
```

## SecurityConfig 配置

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> 
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/api/articles/**").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(jwtAuthenticationFilter, 
            UsernamePasswordAuthenticationFilter.class);
    return http.build();
}
```

## 测试验证

### 登录获取 Token

```bash
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d ''{"username":"admin","password":"admin123"}''
```

### 使用 Token 创建文章

```bash
curl -X POST http://localhost:8081/api/articles/ \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d ''{"title":"测试文章","content":"内容"}''
```

## 总结

JWT 认证的核心原则：
1. ✅ **永远不要信任客户端传入的用户 ID**
2. ✅ **Token 必须在服务端验证**
3. ✅ **用户信息从 Token 解析，不从请求参数获取**
4. ✅ **使用 Filter 统一处理认证逻辑**

---

*作者：酱肉 (Jiangrou) | 发布日期：2026-03-27 | 标签：JWT、Spring Security、认证*',
'详解 JWT 认证在博客系统中的实现，从请求参数到 Token 解析的安全改进',
5,
'PUBLISHED',
0,
0,
NOW(),
NOW()
);

-- 文章 3: 前端组件集成
INSERT INTO articles (title, content, summary, author_id, status, access_level, view_count, created_at, updated_at) 
VALUES (
'Vue 3 组件集成：分类选择器 + 标签输入框',
'# Vue 3 组件集成：分类选择器 + 标签输入框

## 背景

在文章编辑页面中，我们需要实现两个关键功能：
1. 分类选择器 - 从已有分类中选择
2. 标签输入框 - 支持输入新标签或选择已有标签

## 分类选择器实现

### 组件结构

```vue
<template>
  <div class="category-select">
    <el-select v-model="selectedCategory" placeholder="选择分类">
      <el-option
        v-for="category in categories"
        :key="category.id"
        :label="category.name"
        :value="category.id"
      />
    </el-select>
  </div>
</template>
```

### 数据加载

```typescript
const categories = ref<Category[]>([])

const loadCategories = async () => {
  try {
    const response = await fetch(''/api/categories/'')
    categories.value = await response.json()
  } catch (error) {
    console.error(''加载分类失败:'', error)
  }
}

onMounted(() => {
  loadCategories()
})
```

## 标签输入框实现

### 支持输入和选择

```vue
<template>
  <div class="tag-input">
    <el-select
      v-model="selectedTags"
      multiple
      filterable
      allow-create
      default-first-option
      placeholder="输入或选择标签"
    >
      <el-option
        v-for="tag in tags"
        :key="tag.id"
        :label="tag.name"
        :value="tag.id"
      />
    </el-select>
  </div>
</template>
```

### 标签数据处理

```typescript
const tags = ref<Tag[]>([])
const selectedTags = ref<number[]>([])

const loadTags = async () => {
  try {
    const response = await fetch(''/api/tags/'')
    tags.value = await response.json()
  } catch (error) {
    console.error(''加载标签失败:'', error)
  }
}
```

## 表单验证

### 验证规则

```typescript
const rules = {
  title: [
    { required: true, message: ''请输入标题'', trigger: ''blur'' },
    { min: 5, max: 100, message: ''长度在 5 到 100 个字符'', trigger: ''blur'' }
  ],
  content: [
    { required: true, message: ''请输入内容'', trigger: ''blur'' }
  ],
  category: [
    { required: true, message: ''请选择分类'', trigger: ''change'' }
  ]
}
```

### 提交处理

```typescript
const submitForm = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const response = await fetch(''/api/articles/'', {
          method: ''POST'',
          headers: {
            ''Content-Type'': ''application/json'',
            ''Authorization'': `Bearer ${token.value}`
          },
          body: JSON.stringify({
            title: formData.title,
            content: formData.content,
            categoryId: selectedCategory.value,
            tagIds: selectedTags.value
          })
        })
        
        if (response.ok) {
          ElMessage.success(''发布成功'')
          router.push(''/articles'')
        }
      } catch (error) {
        ElMessage.error(''发布失败'')
      }
    }
  })
}
```

## API 集成

### 文章创建接口

```typescript
interface ArticleCreateDTO {
  title: string
  content: string
  summary?: string
  categoryId?: number
  tagIds?: number[]
  status?: ''DRAFT'' | ''PUBLISHED''
}
```

### 响应处理

```typescript
interface ArticleResponseDTO {
  id: number
  title: string
  content: string
  contentHtml: string
  summary: string
  status: string
  createdAt: string
  categories: CategoryDTO[]
  tags: TagDTO[]
}
```

## 样式优化

```css
.article-edit {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.form-section {
  margin-bottom: 24px;
}

.form-section label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
}

.editor-container {
  min-height: 400px;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
}
```

## 总结

关键要点：
1. ✅ 使用 Element Plus 组件库提高开发效率
2. ✅ 支持标签的输入和选择（allow-create + filterable）
3. ✅ 完整的表单验证流程
4. ✅ 统一的错误处理机制

---

*作者：豆沙 (Dousha) | 发布日期：2026-03-27 | 标签：Vue 3、前端、组件开发*',
'详解 Vue 3 文章编辑页面的分类选择器和标签输入框实现',
5,
'PUBLISHED',
0,
0,
NOW(),
NOW()
);

SELECT id, title, status, LEFT(summary, 30) as summary FROM articles ORDER BY created_at DESC;
