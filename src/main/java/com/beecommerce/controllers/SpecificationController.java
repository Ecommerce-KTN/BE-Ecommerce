package com.beecommerce.controllers;

import com.beecommerce.dto.response.ApiResponse;
import com.beecommerce.dto.response.SpecificationResponse;
import com.beecommerce.mapper.SpecificationMapper;
import com.beecommerce.repositories.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specifications")
@CrossOrigin(origins = "http://localhost:3000")
public class SpecificationController {
    @Autowired
    private SpecificationRepository specificationRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SpecificationResponse>>> getAllSpecifications() {
        try {
            List<SpecificationResponse> response = specificationRepository.findAll().stream()
                    .map(SpecificationMapper.INSTANCE::convertToResponse)
                    .toList();
            return ResponseEntity.ok(
                    ApiResponse.<List<SpecificationResponse>>builder()
                            .data(response)
                            .message("Specifications fetched successfully")
                            .status(HttpStatus.OK.value())
                            .success(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.<List<SpecificationResponse>>builder()
                            .error(null)
                            .message("Error fetching specifications: " + e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .success(false)
                            .build());
        }
    }
    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<ApiResponse<SpecificationResponse>> getOneSpecification(String id) {
        try {
            SpecificationResponse response = SpecificationMapper.INSTANCE.convertToResponse(specificationRepository.findByCategoryId(id));
            return ResponseEntity.ok(
                    ApiResponse.<SpecificationResponse>builder()
                            .data(response)
                            .message("Specification fetched successfully")
                            .status(HttpStatus.OK.value())
                            .success(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.<SpecificationResponse>builder()
                            .error(null)
                            .message("Error fetching specification: " + e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .success(false)
                            .build());
        }
    }
}
