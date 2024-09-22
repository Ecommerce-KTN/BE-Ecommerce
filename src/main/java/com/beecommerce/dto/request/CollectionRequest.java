package com.beecommerce.dto.reponse;

import com.beecommerce.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectionRequest {
    private String id;
    private String name;
    private String banner;
    private String brand;
    private String shopId;
    private List<Product> products;
    private Date createdTime;
    private boolean isFeatured;
    private boolean isPaidForHomeLanding;}
