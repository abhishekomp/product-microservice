package org.aom.product_service.service;

import org.aom.product_service.dto.CreateProductRequest;
import org.aom.product_service.dto.DiscountedPriceRequest;
import org.aom.product_service.dto.DiscountedPriceResponse;
import org.aom.product_service.model.Product;
import org.aom.product_service.model.ProductCategory;
import org.aom.product_service.repository.ProductRepository;
import org.aom.product_service.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author : Abhishek
 * @since : 2024-08-26, Monday
 **/
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductServiceToDiscountServiceCommunicator productServiceToDiscountServiceCommunicator;

    private final ProductRepository productRepository;

    public ProductService(ProductServiceToDiscountServiceCommunicator productServiceToDiscountServiceCommunicator, ProductRepository productRepository) {
        this.productServiceToDiscountServiceCommunicator = productServiceToDiscountServiceCommunicator;
        this.productRepository = productRepository;
    }

    public Product getProductById(Long productId){
        Optional<Product> byId = productRepository.findById(productId);
        return byId.orElseThrow(() -> new ProductNotFoundException("No product(s) found for productId: " + productId));
    }

    public List<Product> getProductByCategory(String productCategory) {
        Iterable<Product> byProductCategory = productRepository.findByProductCategory(ProductCategory.valueOf(productCategory.toUpperCase()));

        return (List<Product>) byProductCategory;
    }

    public Product save(CreateProductRequest productRequest) {
        Product productToAdd = new Product();
        productToAdd.setProductName(productRequest.getProductName());
        productToAdd.setProductCode(productRequest.getProductCode());
        productToAdd.setProductCategory(ProductCategory.valueOf(productRequest.getProductCategory()));
        productToAdd.setSalePrice(productRequest.getSalePrice());
        return productRepository.save(productToAdd);
    }

    /**
     * This will call another spring rest service to calculate the discounted price
     * @param discountedPriceRequest request
     * @return discountedPriceResponse response
     */
    public DiscountedPriceResponse calculateDiscountedPrice(DiscountedPriceRequest discountedPriceRequest) {
        logger.info("ProductService:calculateDiscountedPrice() called with discountedPriceRequest: {}", discountedPriceRequest);
        DiscountedPriceResponse discountedPriceResponse = productServiceToDiscountServiceCommunicator.invokeCalculateDiscount(discountedPriceRequest);
//        DiscountedPriceResponse discountedPriceResponse = new DiscountedPriceResponse();
//        discountedPriceResponse.setDiscountedPrice(BigDecimal.valueOf(2000.0));
//        discountedPriceResponse.setProductCode("APP2024");
//        discountedPriceResponse.setCurrency("USD");
//        discountedPriceResponse.setDiscountCode("APP2024");
        logger.info("ProductService:calculateDiscountedPrice() received response from discount service: {}", discountedPriceResponse);
        return discountedPriceResponse;
    }
}
