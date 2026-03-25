FROM eclipse-temurin:21-jre

# 设置时区为 Asia/Shanghai
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 创建应用目录
WORKDIR /app

# 复制 JAR 文件到容器中
COPY target/backend-1.0.0-SNAPSHOT.jar app.jar

# 暴露端口 8081
EXPOSE 8081

# 启动应用
ENTRYPOINT ["java", "-jar", "app.jar"]