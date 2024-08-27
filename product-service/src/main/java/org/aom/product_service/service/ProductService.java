package org.aom.product_service.service;

import org.aom.product_service.model.Product;
import org.aom.product_service.model.ProductCategory;
import org.aom.product_service.repository.ProductRepository;
import org.aom.product_service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Abhishek
 * @since : 2024-08-26, Monday
 **/
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
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

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
