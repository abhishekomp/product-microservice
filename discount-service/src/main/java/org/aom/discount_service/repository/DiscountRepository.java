package org.aom.discount_service.repository;

import org.aom.discount_service.model.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {
    Optional<Discount> findByProductCode(String productCode);
    Optional<Discount> findByProductCodeAndDiscountCode(String productCode, String discountCode);
}
