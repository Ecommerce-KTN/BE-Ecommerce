# Sử dụng base image với JDK 21
FROM eclipse-temurin:21-jdk

# Đặt biến môi trường để không hỏi trong Gradle
ENV GRADLE_OPTS "-Dorg.gradle.daemon=false"

# Cài đặt Gradle bằng cách sử dụng hình ảnh Gradle chính thức
# Sử dụng gradle wrapper có trong project để đảm bảo phiên bản phù hợp
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

# Expose port nếu cần chạy ứng dụng
EXPOSE 8080

# Chạy ứng dụng sau khi build (tùy thuộc vào ứng dụng của bạn)
CMD ["java", "-jar", "build/libs/your-app.jar"]
