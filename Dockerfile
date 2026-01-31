# ===== 빌드 =====
FROM eclipse-temurin:21-jdk AS builder
LABEL authors="Sooyoung"
WORKDIR /workspace

# 캐시 효율을 위해 gradle wrapper + build files 먼저 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle* settings.gradle* ./
RUN chmod +x gradlew

# 소스 복사
COPY src src

# 테스트는 추후 추가
RUN ./gradlew clean bootJar -x test --no-daemon

# ===== 런타임 =====
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /workspace/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]