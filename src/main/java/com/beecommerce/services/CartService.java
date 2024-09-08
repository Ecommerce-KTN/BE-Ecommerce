package com.beecommerce.services;

import com.beecommerce.exception.ErrorCode;
import com.beecommerce.exception.Exception;
import com.beecommerce.models.OrderDetail;
import com.beecommerce.models.Product;
import com.beecommerce.repositories.OrderDetailRepository;
import com.beecommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private OrderDetailRepository orderDetailRepository;
//
//    public String addItemToCart(String productId, int quantity){
//        String stockMessage = productService.checkStockAvailability(productId, quantity);
//
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new Exception(ErrorCode.PRODUCT_NOT_FOUND));
//
//        if (!"Stock is sufficient.".equals(stockMessage)) {
//            return stockMessage;
//        }
//        createAndSaveOrderDetail(product, quantity);
//        return "Item added to cart.";
//    }
//    private void createAndSaveOrderDetail(Product product, int quantity) {
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setProduct(product);
//        orderDetail.setQuantity(quantity);
//        orderDetailRepository.save(orderDetail);
//    }


}
