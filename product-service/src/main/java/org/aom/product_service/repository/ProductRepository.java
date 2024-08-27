package org.aom.product_service.repository;

import org.aom.product_service.model.Product;
import org.aom.product_service.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Abhishek
 * @since : 2024-08-26, Monday
 **/
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findByProductCategory(ProductCategory productCategory);
}
