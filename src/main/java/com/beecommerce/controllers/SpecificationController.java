package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.ApiResponse;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.models.Specification;
import com.beecommerce.services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/specifications")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<List<Specification>>> getSpecificationsByCategoryId(@PathVariable String categoryId) {
        try {
            List<Specification> specifications = specificationService.getSpecificationsByCategoryId(categoryId);

            if (specifications.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.<List<Specification>>builder()
                                .success(false)
                                .message(ErrorCode.SPECIFICATION_NOT_FOUND.getMessage())
                                .data(null)
                                .build());
            }

            return ResponseEntity.ok(ApiResponse.<List<Specification>>builder()
                    .success(true)
                    .data(specifications)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.<List<Specification>>builder()
                            .success(false)
                            .message(ErrorCode.SPECIFICATION_NOT_FOUND.getMessage())
                            .data(null)
                            .build());
        }
    }
}
