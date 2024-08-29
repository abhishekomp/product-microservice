package org.aom.discount_service.controller;

import org.aom.discount_service.dto.DiscountRequest;
import org.aom.discount_service.dto.DiscountResponse;
import org.aom.discount_service.exception.DiscountExpiredException;
import org.aom.discount_service.exception.DiscountNotFoundException;
import org.aom.discount_service.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
@RestController
@RequestMapping("/discount-api")
public class DiscountController {

    private static final Logger logger = LoggerFactory.getLogger(DiscountController.class);
    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping("/calculateDiscount")
    public ResponseEntity<DiscountResponse> getDiscountedPrice(@RequestBody DiscountRequest discountRequest) {
        logger.info("DiscountController:getDiscountedPrice() called with discountRequest: {}", discountRequest);
        DiscountResponse discountResponse = null;
        discountResponse = discountService.calculateDiscount(discountRequest);
        return ResponseEntity.ok(discountResponse);
    }

    @GetMapping("/all")
    public String testEndPoint(){
        throw new DiscountExpiredException("Testing exception");
    }
}
