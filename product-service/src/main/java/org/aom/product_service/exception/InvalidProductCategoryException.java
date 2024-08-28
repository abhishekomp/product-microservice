package org.aom.product_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : Abhishek
 * @since : 2024-08-26, Monday
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidProductCategoryException extends RuntimeException{
    public InvalidProductCategoryException(String msg){
        super(msg);
    }
}
