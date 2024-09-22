package com.beecommerce.controllers;

import com.beecommerce.dto.reponse.ApiResponse;
import com.beecommerce.dto.reponse.CollectionRequest;
import com.beecommerce.dto.reponse.CollectionResponse;
import com.beecommerce.mapper.CollectionMapper;
import com.beecommerce.models.Collection;
import com.beecommerce.services.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/collections")
@CrossOrigin(origins = "http://localhost:3000")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @PostMapping
    public ResponseEntity<ApiResponse<CollectionResponse>> createCollection(@RequestBody CollectionRequest request) {
        try {
            Collection collection = CollectionMapper.INSTANCE.convertToEntity(request);

            Collection createdCollection = collectionService.createCollection(collection);

            CollectionResponse response = CollectionMapper.INSTANCE.convertToResponse(createdCollection);

            return ResponseEntity.ok(
                    ApiResponse.<CollectionResponse>builder()
                            .data(response)
                            .message("Collection created successfully")
                            .status(HttpStatus.OK.value())
                            .success(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.<CollectionResponse>builder()
                            .error(null)
                            .message("Error creating collection: " + e.getMessage())
                            .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                            .success(false)
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CollectionResponse>> getCollectionById(@PathVariable String id) {
        try {
            Collection collection = collectionService.getCollectionById(id);
            CollectionResponse response = CollectionMapper.INSTANCE.convertToResponse(collection);

            return ResponseEntity.ok(
                    ApiResponse.<CollectionResponse>builder()
                            .data(response)
                            .message("Collection retrieved successfully")
                            .status(HttpStatus.OK.value())
                            .success(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<CollectionResponse>builder()
                            .error(null)
                            .message("Error retrieving collection: " + e.getMessage())
                            .status(HttpStatus.NOT_FOUND.value())
                            .success(false)
                            .build());
        }
    }
}
