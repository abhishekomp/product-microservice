package org.aom.discount_service.exception;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
public class DiscountExpiredException extends RuntimeException {
    public DiscountExpiredException(String discount_has_expired) {
        super(discount_has_expired);
    }
}
