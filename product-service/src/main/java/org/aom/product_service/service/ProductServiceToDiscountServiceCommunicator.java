package org.aom.product_service.service;

import org.aom.product_service.dto.DiscountedPriceRequest;
import org.aom.product_service.dto.DiscountedPriceResponse;
import org.aom.product_service.exception.ExternalServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * @author : Abhishek
 * @since : 2024-08-29, Thursday
 **/
@Service
public class ProductServiceToDiscountServiceCommunicator {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceToDiscountServiceCommunicator.class);
    //public static final String DISCOUNT_SERVICE = "http://discount-service/discount-api/";
    public static final String DISCOUNT_SERVICE = "http://localhost:8091/discount-api/";

    private final RestTemplate restTemplate;

    public ProductServiceToDiscountServiceCommunicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DiscountedPriceResponse invokeCalculateDiscount(DiscountedPriceRequest discountedPriceRequest) {
        try{
            ResponseEntity<DiscountedPriceResponse> responseEntity = restTemplate.postForEntity(DISCOUNT_SERVICE + "calculateDiscount", discountedPriceRequest, DiscountedPriceResponse.class);
            DiscountedPriceResponse discountedPriceResponse = responseEntity.getBody();
            logger.info("ProductServiceToDiscountServiceCommunicator:invokeCalculateDiscount() received status code '{}' and response for service to service communication: {}", responseEntity.getStatusCode(), discountedPriceResponse);
            return discountedPriceResponse;
        } catch(HttpStatusCodeException e){
            logger.info("ProductServiceToDiscountServiceCommunicator:invokeCalculateDiscount() received exception: {}", e.getStatusCode().value());
            logger.info("ProductServiceToDiscountServiceCommunicator:invokeCalculateDiscount() received exception: {}", e.getResponseBodyAsString());
            throw new ExternalServiceException(e.getResponseBodyAsString());
        }
//        catch (RestClientException e) {
//            // Handle the exception
//
//            logger.info("ProductServiceToDiscountServiceCommunicator:invokeCalculateDiscount() received exception: {}", e.getMessage());
//            throw new RuntimeException("Something happened");
//        }
    }
}
