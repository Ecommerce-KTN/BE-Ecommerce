package com.beecommerce.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class S3Service {
    @Value("${aws.s3.access-key}")
    private String accessKey;

    @Value("${aws.s3.secret-key}")
    private String secretKey;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    private final S3Client s3Client = S3Client.builder()
            .region(Region.AP_SOUTHEAST_2)
            .credentialsProvider(() -> AwsBasicCredentials.create(accessKey, secretKey))
            .build();

    public String uploadFileToS3(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(uniqueFileName)
//                            .acl(ObjectCannedACL.PUBLIC_READ)
                            .build(),
                    software.amazon.awssdk.core.sync.RequestBody.fromBytes(file.getBytes()));
        } catch (S3Exception e) {
            try {
                e.printStackTrace();
                throw new IOException("Failed to upload to S3");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(uniqueFileName)).toExternalForm();
    }
}
