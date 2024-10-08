package org.aom.product_service.controller;

import jakarta.validation.Valid;
import org.aom.product_service.dto.CreateProductRequest;
import org.aom.product_service.dto.DiscountedPriceRequest;
import org.aom.product_service.dto.DiscountedPriceResponse;
import org.aom.product_service.exception.InvalidProductCategoryException;
import org.aom.product_service.exception.ProductNotFoundException;
import org.aom.product_service.model.ApiError;
import org.aom.product_service.model.Product;
import org.aom.product_service.model.ProductCategory;
import org.aom.product_service.service.ProductService;
import org.aom.product_service.validation.ValueOfEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Abhishek
 * @since : 2024-08-26, Monday
 **/
@RestController
@RequestMapping("product-api")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProductById/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable long productId){
        logger.info("ProductController:getProductById() called with productId: {}", productId);
        Product productById = productService.getProductById(productId);
        return ResponseEntity.ok().body(productById);
    }

    @GetMapping("/getProductByCategory/{productCategory}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String productCategory){
        logger.info("ProductController:getProductByCategory() called with productCategory: {}", productCategory);
        boolean isValidProductCategory = Arrays.stream(ProductCategory.values()).anyMatch((t) -> t.name().equals(productCategory));
        if(!isValidProductCategory){
            throw new InvalidProductCategoryException("productCategory should be one of these: " + Arrays.toString(ProductCategory.values()));
        }
        List<Product> products = productService.getProductByCategory(productCategory);
        logger.info("ProductController:getProductByCategory() with productCategory found {} products", products.size());
        if(products.size() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            //return ResponseEntity.noContent();
        }
        return ResponseEntity.ok().body(products);
    }

    @PostMapping("/addNewProduct")
    public Product addNewProduct(@RequestBody @Valid CreateProductRequest productRequest){
        return productService.save(productRequest);
    }

    @PostMapping("/calculateDiscountedPrice")
    public DiscountedPriceResponse calculateDiscountedPrice(@RequestBody DiscountedPriceRequest discountedPriceRequest){
        logger.info("ProductController:calculateDiscountedPrice() was invoked with discountedPriceRequest: {}", discountedPriceRequest);
        DiscountedPriceResponse discountedPriceResponse = productService.calculateDiscountedPrice(discountedPriceRequest);
        logger.info("ProductController:calculateDiscountedPrice() received in response discountedPriceResponse: {}", discountedPriceResponse);
        return discountedPriceResponse;
    }



//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException ex){
//        logger.info("ProductController:handleProductNotFoundException() called with exception: {}", ex.getMessage());
//        ApiError errObj = new ApiError();
//        errObj.setErrCode(HttpStatus.NOT_FOUND.value());
//        errObj.setErrorMsg(ex.getMessage());
//        errObj.setErrorDateTime(LocalDateTime.now());
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.add("my-custom-header", "my-custom-value-exception");
//        //return ResponseEntity.notFound().headers(responseHeaders).body(errObj);
//        //return ResponseEntity.ok().headers(responseHeaders).body(errObj);
//        return new ResponseEntity<>(errObj, responseHeaders, HttpStatus.NOT_FOUND);
//    }


}
