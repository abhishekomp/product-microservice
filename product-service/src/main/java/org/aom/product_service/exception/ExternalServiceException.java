package org.aom.product_service.exception;

/**
 * @author : Abhishek
 * @since : 2024-08-29, Thursday
 **/
public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String msg) {
        super(msg);
    }
}
