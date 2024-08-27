package org.aom.product_service.exception;

import org.aom.product_service.controller.ProductController;
import org.aom.product_service.model.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * @author : Abhishek
 * @since : 2024-08-27, Tuesday
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleProductNotFoundException(ProductNotFoundException ex){
        logger.info("GlobalExceptionHandler:handleProductNotFoundException() called with exception: {}", ex.getMessage());
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        logger.info("GlobalExceptionHandler:handleMethodArgumentNotValidException() called with exception: {}", ex.getMessage());
        String customException = ex.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getField() + " " + x.getDefaultMessage())
                .collect(Collectors.joining(", "));
        ApiError errObj = new ApiError();
        errObj.setErrCode(HttpStatus.BAD_REQUEST.value());
        errObj.setErrorMsg(customException);
        errObj.setErrorDateTime(LocalDateTime.now());
        return  ResponseEntity.badRequest().body(errObj);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        logger.info("GlobalExceptionHandler:HttpRequestMethodNotSupportedException() called with exception: {}", ex.getMessage());
        ApiError errObj = new ApiError();
        errObj.setErrCode(HttpStatus.METHOD_NOT_ALLOWED.value());
        errObj.setErrorMsg(ex.getMessage());
        errObj.setErrorDateTime(LocalDateTime.now());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("my-custom-header", "my-custom-value-exception");
        //return ResponseEntity.notFound().headers(responseHeaders).body(errObj);
        //return ResponseEntity.ok().headers(responseHeaders).body(errObj);
        return new ResponseEntity<>(errObj, responseHeaders, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> handleNoResourceFoundException(NoResourceFoundException ex){
        logger.info("GlobalExceptionHandler:NoResourceFoundException() called with exception: {}", ex.getMessage());
        ApiError errObj = new ApiError();
        errObj.setErrCode(HttpStatus.BAD_REQUEST.value());
        errObj.setErrorMsg(ex.getMessage());
        errObj.setErrorDateTime(LocalDateTime.now());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("my-custom-header", "my-custom-value-exception");
        return new ResponseEntity<>(errObj, responseHeaders, HttpStatus.BAD_REQUEST);
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
