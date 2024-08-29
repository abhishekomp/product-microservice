package org.aom.discount_service.exception;

import org.aom.discount_service.model.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DiscountNotFoundException.class)
    public ResponseEntity<ApiError> handleDiscountNotFoundException(DiscountNotFoundException ex){
        logger.info("GlobalExceptionHandler:handleDiscountNotFoundException() called with exception: {}", ex.getMessage());
        ApiError errObj = new ApiError();
        errObj.setErrCode(HttpStatus.NOT_FOUND.value());
        errObj.setErrorMsg(ex.getMessage());
        errObj.setErrorDateTime(LocalDateTime.now());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("my-custom-header", "my-custom-value-exception");
        //return ResponseEntity.notFound().headers(responseHeaders).body(errObj);
        //return ResponseEntity.ok().headers(responseHeaders).body(errObj);
        return new ResponseEntity<>(errObj, responseHeaders, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DiscountExpiredException.class)
    public ResponseEntity<ApiError> handleDiscountExpiredException(DiscountExpiredException ex){
        logger.info("GlobalExceptionHandler:handleDiscountExpiredException() called with exception: {}", ex.getMessage());
        ApiError errObj = new ApiError();
        errObj.setErrCode(HttpStatus.NOT_FOUND.value());
        errObj.setErrorMsg(ex.getMessage());
        errObj.setErrorDateTime(LocalDateTime.now());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("my-custom-header", "my-custom-value-exception");
        //return ResponseEntity.notFound().headers(responseHeaders).body(errObj);
        //return ResponseEntity.ok().headers(responseHeaders).body(errObj);
        return new ResponseEntity<>(errObj, responseHeaders, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllOtherException(Exception ex){
        logger.info("GlobalExceptionHandler:handleAllOtherException() called with exception: {}", ex.getMessage());
        logger.info("GlobalExceptionHandler:handleAllOtherException() called with exception name : {}", ex.getClass().getName());
        ApiError errObj = new ApiError();
        errObj.setErrCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errObj.setErrorMsg(ex.getMessage());
        errObj.setErrorDateTime(LocalDateTime.now());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("my-custom-header", "my-custom-value-exception");
        return new ResponseEntity<>(errObj, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
