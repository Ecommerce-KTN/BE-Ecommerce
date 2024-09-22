# Sử dụng base image với JDK 21
FROM eclipse-temurin:21-jdk AS build

# Đặt biến môi trường để không hỏi trong Gradle
ENV GRADLE_OPTS "-Dorg.gradle.daemon=false"

# Cài đặt Gradle bằng cách sử dụng hình ảnh Gradle chính thức
COPY gradlew /app/gradlew
COPY gradle /app/gradle

# Sao chép mã nguồn vào container
COPY . /app

# Đặt thư mục làm việc
WORKDIR /app

# Cấp quyền cho gradlew để có thể thực thi
RUN chmod +x gradlew

# Tải dependencies trước khi build
RUN ./gradlew --no-daemon dependencies

# Build ứng dụng
RUN ./gradlew --no-daemon build

# Tạo image cuối cùng
FROM eclipse-temurin:21-jdk

# Sao chép file jar đã build từ stage trước
COPY --from=build /app/build/libs/BE-Ecommerce-0.0.1-SNAPSHOT.jar app.jar

# Expose port nếu cần chạy ứng dụng
EXPOSE 8080

# Chạy ứng dụng sau khi build
CMD ["java", "-jar", "app.jar"]
