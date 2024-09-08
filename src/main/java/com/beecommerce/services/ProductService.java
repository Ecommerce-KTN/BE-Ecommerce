package com.beecommerce.services;

import com.beecommerce.dto.reponse.ProductResponse;
import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.models.*;
import com.beecommerce.repositories.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductInterface {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Autowired
    private ProductRepository productRepository;
//    @Autowired
//    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;
    private final S3Service s3Service;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, String primaryImage, List<String> images) {
        Product product = convertToEntity(productRequest);
        product.setPrimaryImage(primaryImage);
        product.setImages(images);
        Product savedProduct = productRepository.save(product);
//        return convertToResponse(savedProduct);
        return null;
    }


//    @Override
//    public Optional<ProductResponse> updateProduct(String id, ProductRequest productRequest) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
//
//        BeanUtils.copyProperties(productRequest, product, "id");
//        Product updatedProduct = productRepository.save(product);
//        return Optional.of(convertToResponse(updatedProduct));
//    }

//    @Override
//    public List<ProductResponse> getAllProduct() {
//        return productRepository.findAllByOrderByCreatedTimeDesc()
//                .stream()
//                .map(this::convertToResponse)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public Optional<ProductResponse> getProductById(String id) {
//        return Optional.ofNullable(productRepository.findById(id).map(this::convertToResponse)
//                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND)));
//    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
        productRepository.delete(product);
    }

    //Favorite
    public Optional<ProductResponse> toggleFavorite(String id) {
        Optional<Product> productOpt = productRepository.findById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            productRepository.save(product);
            return Optional.of(new ProductResponse(product));
        }
        return Optional.empty();
    }

//    public String checkStockAvailability(String productId, int quantity) {
//        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
//        int currentStok = product.getOrderDetails().stream().filter(orderDetail -> orderDetail.getProduct().getId().equals(productId)).mapToInt(OrderDetail::getQuantity).sum();
//        if (currentStok >= quantity) {
//            return "Stock is sufficient.";
//        } else {
//            return "Insufficient stock available.";
//        }
//    }

    private Product convertToEntity(ProductRequest productRequest) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        product.setCreatedTime(new Date());

        return product;
    }

//    public ProductResponse convertToResponse(Product product) {
//        ProductResponse response = new ProductResponse();
//        BeanUtils.copyProperties(product, response);
//        response.setCategoryId(product.getCategory().getId());
//        response.setCreateTime(new Date());
//        response.setCategoryName(product.getCategory().getName());
//        String parentCategoryName = categoryRepository.findById(product.getParentCategoryId()).get().getName();
//        response.setParentCategoryName(parentCategoryName);
//        return response;
//    }

    public boolean isProductNameUnique(String productName) {
        return productRepository.findByName(productName).isEmpty();
    }

//    public void addReviewToProduct(ReviewRequest reviewRequest) {
//        Product product = productRepository.findById(reviewRequest.getProductId()).orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
//
//        Customer customer = customerRepository.findById(reviewRequest.getCustomerId()).orElseThrow(() -> new Exception(ErrorCode.CUSTOMER_NOT_FOUND));
//
//        Review review = new Review();
//        review.setProduct(product);
//        review.setCustomer(customer);
//        review.setRating(reviewRequest.getRating());
//        review.setComment(reviewRequest.getComment());
//        reviewRepository.save(review);
//
//        product.getReviews().add(review);
//        productRepository.save(product);
//
//        throw new Exception(SuccessCode.PRODUCT_ADD_REVIEW);
//    }
}
