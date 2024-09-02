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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductInterface {

    @Autowired
    private CostPriceRepository costPriceRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private DiscountPriceRepository discountPriceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;
    private final S3Service s3Service;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest, String primaryImage, List<String> images) {
        Product product = convertToEntity(productRequest);
        product.setPrimaryImage(primaryImage);
        product.setImages(images);
        Product savedProduct = productRepository.save(product);
        return convertToResponse(savedProduct);
    }

    @Override
    public Optional<ProductResponse> updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));

        BeanUtils.copyProperties(productRequest, product, "id");
        Product updatedProduct = productRepository.save(product);
        return Optional.of(convertToResponse(updatedProduct));
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll().stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductResponse> getProductById(String id) {
        return Optional.ofNullable(productRepository.findById(id).map(this::convertToResponse).orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND)));
    }

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

    public String checkStockAvailability(String productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
        int currentStok = product.getOrderDetails().stream().filter(orderDetail -> orderDetail.getProduct().getId().equals(productId)).mapToInt(OrderDetail::getQuantity).sum();
        if (currentStok >= quantity) {
            return "Stock is sufficient.";
        } else {
            return "Insufficient stock available.";
        }
    }


//    public Product convertToEntity(ProductRequest productRequest) {
//        Product product = new Product();
//        BeanUtils.copyProperties(productRequest, product);
//        return product;
//    }

    private Product convertToEntity(ProductRequest productRequest) {
        Product product = new Product();
        BeanUtils.copyProperties(productRequest, product);
        // Set Category by id
        if (!StringUtils.isEmpty(productRequest.getCategoryId())) {
            Category category = categoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new Exception(ErrorCode.CATEGORY_NOT_FOUND));
            category.getProducts().add(product);
            product.setCategory(category);
        }

//        // Set Cost Price
//        if (productRequest.getCostPrice() != null) {
//            CostPrice costPrice = new CostPrice();
//            costPrice.setPrice(productRequest.getCostPrice());
//            costPrice.setEffectiveDate(LocalDateTime.now());
//            costPrice.setProduct(product);
//            costPriceRepository.save(costPrice);
//            product.setCostPrices(List.of(costPrice));
//        }
//
//        // Set Prices
//        if (productRequest.getPrice() != null) {
//            Price price = new Price();
//            price.setPrice(productRequest.getPrice());
//            price.setEffectiveDate(LocalDateTime.now());
//            price.setProduct(product);
//            priceRepository.save(price);
//            product.setPrices(List.of(price));
//
//        }
//
//        // Set Discount Prices
//        if (productRequest.getDiscountPrice() != null) {
//            DiscountPrice discountPrice = new DiscountPrice();
//            discountPrice.setPrice(productRequest.getDiscountPrice());
//            discountPrice.setEffectiveDate(LocalDateTime.now());
//            discountPrice.setProduct(product);
//            discountPriceRepository.save(discountPrice);
//            product.setDiscountPrices(List.of(discountPrice));
//        }
        return product;
    }

    public ProductResponse convertToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        BeanUtils.copyProperties(product, response);
        response.setCategoryId(product.getCategory().getId());
//        response.setCostPrice(product.getCostPrices().get(0).getPrice());
//        response.setPrice(product.getPrices().get(0).getPrice());
//        response.setDiscountPrice(product.getDiscountPrices().get(0).getPrice());
        return response;
    }

    public boolean isProductNameUnique(String productName) {
        return productRepository.findByName(productName).isEmpty();
    }

    public void addReviewToProduct(ReviewRequest reviewRequest) {
        Product product = productRepository.findById(reviewRequest.getProductId()).orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));

        Customer customer = customerRepository.findById(reviewRequest.getCustomerId()).orElseThrow(() -> new Exception(ErrorCode.CUSTOMER_NOT_FOUND));

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
