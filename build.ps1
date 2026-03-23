# 后端构建脚本
# 设置 JAVA_HOME
$env:JAVA_HOME = "F:\jdk\21"
$env:PATH = "$env:JAVA_HOME\bin;" + $env:PATH

Write-Host "======= 后端构建开始 =======" -ForegroundColor Green
Write-Host "JAVA_HOME: $env:JAVA_HOME" -ForegroundColor Yellow

# 检查 Java 版本
Write-Host "Java Version:" -ForegroundColor Yellow
java -version

# 检查 Maven 版本
Write-Host "Maven Version:" -ForegroundColor Yellow
mvn -version

# 进入后端项目目录
Set-Location "F:\openclaw\code\backend"

# 执行 Maven 构建
Write-Host "执行 mvn clean package -DskipTests..." -ForegroundColor Yellow
mvn clean package -DskipTests

if ($LASTEXITCODE -eq 0) {
    # 查找生成的 jar 包
    $jarFiles = Get-ChildItem -Path "target" -Filter "*.jar" -Recurse
    foreach ($jarFile in $jarFiles) {
        Write-Host "✅ 构建成功! JAR 包路径: $($jarFile.FullName)" -ForegroundColor Green
    }
} else {
    Write-Error "❌ 构建失败!"
    exit 1
}

Write-Host "======= 后端构建完成 =======" -ForegroundColor Green