# 使用官方的 OpenJDK 基底映像
FROM openjdk:17-jdk-slim

# 設定容器內的工作目錄
WORKDIR /app

# 複製 Maven 構建的 JAR 檔案到容器中
COPY target/*.jar app.jar

# 開放容器的 8080 端口
EXPOSE 8080

# 容器啟動時執行 Spring Boot 應用程式
ENTRYPOINT ["java", "-jar", "app.jar"]
