package org.aom.discount_service.exception;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
public class DiscountNotFoundException extends RuntimeException {
    public DiscountNotFoundException(String s) {
        super(s);
    }
}
