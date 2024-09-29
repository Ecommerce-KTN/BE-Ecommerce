package com.beecommerce.controllers;

import com.beecommerce.dto.response.ApiResponse;
import com.beecommerce.dto.reponse.CollectionRequest;
import com.beecommerce.dto.response.CollectionResponse;
import com.beecommerce.mapper.CollectionMapper;
import com.beecommerce.models.Collection;
import com.beecommerce.repositories.CollectionRepository;
import com.beecommerce.services.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collections")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private final CollectionMapper collectionMapper;

    @PostMapping
    public ResponseEntity<ApiResponse<CollectionResponse>> createCollection(@RequestBody CollectionRequest request) {
        try {
            Collection collection = collectionMapper.convertToEntity(request);

            Collection createdCollection = collectionService.createCollection(collection);

            CollectionResponse response = collectionMapper.convertToResponse(createdCollection);

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
//    getAll
    @GetMapping
    public ResponseEntity<ApiResponse<List<CollectionResponse>>> getAllCollections() {
        try {
            List<CollectionResponse> response = collectionRepository.findAll().stream()
                    .map(collectionMapper::convertToResponse)
                    .toList();

            return ResponseEntity.ok(
                    ApiResponse.<List<CollectionResponse>>builder()
                            .data(response)
                            .message("Collections retrieved successfully")
                            .status(HttpStatus.OK.value())
                            .success(true)
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.<List<CollectionResponse>>builder()
                            .error(null)
                            .message("Error retrieving collections: " + e.getMessage())
                            .status(HttpStatus.NOT_FOUND.value())
                            .success(false)
                            .build());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CollectionResponse>> getCollectionById(@PathVariable String id) {
        try {
            Collection collection = collectionService.getCollectionById(id);
            CollectionResponse response = collectionMapper.convertToResponse(collection);

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
