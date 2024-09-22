# Sử dụng image chính thức của Gradle với JDK 19 để build
FROM gradle:7.5.1-jdk19 AS build

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file cấu hình Gradle và tải dependencies
COPY --chown=gradle:gradle build.gradle settings.gradle /app/

# Chạy lệnh tải dependencies để tận dụng cache Docker
RUN gradle --no-daemon build || return 0

# Sao chép toàn bộ mã nguồn vào container
COPY --chown=gradle:gradle src /app/src

# Thực hiện build ứng dụng
RUN gradle clean build --no-daemon

# Sử dụng một image nhẹ hơn để chạy ứng dụng sau khi build xong
FROM openjdk:19-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file JAR đã build từ giai đoạn trước đó
COPY --from=build /app/build/libs/*.jar app.jar

# Khai báo cổng mà ứng dụng sẽ sử dụng
EXPOSE 8080

# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
