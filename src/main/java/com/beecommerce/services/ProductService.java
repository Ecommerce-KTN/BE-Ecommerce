package com.beecommerce.services;

import com.beecommerce.dto.reponse.ProductResponse;
import com.beecommerce.dto.request.ProductRequest;
import com.beecommerce.dto.request.ReviewRequest;
import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.exception.SuccessCode;
import com.beecommerce.models.Customer;
import com.beecommerce.models.OrderDetail;
import com.beecommerce.models.Product;
import com.beecommerce.models.Review;
import com.beecommerce.repositories.CustomerRepository;
import com.beecommerce.repositories.ProductRepository;
import com.beecommerce.repositories.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements ProductInterface {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = convertToEntity(productRequest);
        Product savedProduct = productRepository.save(product);
        return convertToResponse(savedProduct);
    }
    @Override
    public Optional<ProductResponse> updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));

        BeanUtils.copyProperties(productRequest, product, "id");
        Product updatedProduct = productRepository.save(product);
        return Optional.of(convertToResponse(updatedProduct));
    }
    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductResponse> getProductById(String id) {
        return Optional.ofNullable(productRepository.findById(id)
                .map(this::convertToResponse)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
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

    public String checkStockAvailability(String productId, int quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
        int currentStok = product.getOrderDetails().stream()
                .filter(orderDetail -> orderDetail.getProduct().getId().equals(productId))
                .mapToInt(OrderDetail::getQuantity)
                .sum();
        if (currentStok >= quantity) {
            return "Stock is sufficient.";
        } else {
            return "Insufficient stock available.";
        }
    }


    public Product convertToEntity(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        return product;
    }
    public ProductResponse convertToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(product, response);
        return response;
    }

    public boolean isProductNameUnique(String productName) {
        return productRepository.findByName(productName).isEmpty();
    }

    public void addReviewToProduct(ReviewRequest reviewRequest){
        Product product = productRepository.findById(reviewRequest.getProductId())
                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));

        Customer customer = customerRepository.findById(reviewRequest.getCustomerId())
                .orElseThrow(() -> new Exception(ErrorCode.CUSTOMER_NOT_FOUND));

        Review review = new Review();
        review.setProduct(product);
        review.setCustomer(customer);
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        reviewRepository.save(review);

        product.getReviews().add(review);
        productRepository.save(product);

        throw new Exception(SuccessCode.PRODUCT_ADD_REVIEW);
    }
}
