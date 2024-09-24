package com.beecommerce.mapper;

import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.response.ProductResponse;
import com.beecommerce.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)
    @Mapping(target = "avgRating", ignore = true)
    @Mapping(target = "images", expression = "java(mapImages(request.getImages()))")
    @Mapping(target = "primaryImage", expression = "java(request.getPrimaryImage() != null ? request.getPrimaryImage().getOriginalFilename() : null)")
    Product convertToEntity(ProductRequest request);

    default List<String> mapImages(List<MultipartFile> images) {
        if (images == null) {
            return null;
        }
        return images.stream()
                .map(MultipartFile::getOriginalFilename)
                .collect(Collectors.toList());
    }

    ProductResponse convertToResponse(Product product);
}
