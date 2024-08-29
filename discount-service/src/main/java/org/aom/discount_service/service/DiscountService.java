package org.aom.discount_service.service;

import org.aom.discount_service.dto.DiscountRequest;
import org.aom.discount_service.dto.DiscountResponse;
import org.aom.discount_service.exception.DiscountExpiredException;
import org.aom.discount_service.exception.DiscountNotFoundException;
import org.aom.discount_service.model.Discount;
import org.aom.discount_service.repository.DiscountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
@Service
public class DiscountService {
    private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);
    private static final String BASE_CURRENCY = "USD";
    private final DiscountRepository discountRepository;

    private final DiscountServiceToCurrencyConverterServiceCommunicator discountServiceToCurrencyConverterServiceCommunicator;

    public DiscountService(DiscountRepository discountRepository, DiscountServiceToCurrencyConverterServiceCommunicator discountServiceToCurrencyConverterServiceCommunicator) {
        this.discountRepository = discountRepository;
        this.discountServiceToCurrencyConverterServiceCommunicator = discountServiceToCurrencyConverterServiceCommunicator;
    }

    public DiscountResponse calculateDiscount(DiscountRequest discountRequest) {
        //Optional<Discount> optional = discountRepository.findByProductCode(discountRequest.getProductCode());
        Optional<Discount> optional = discountRepository.findByProductCodeAndDiscountCode(discountRequest.getProductCode(), discountRequest.getDiscountCode());
        Discount discount = optional.orElseThrow(() -> new DiscountNotFoundException("No Discount found for productCode: '" + discountRequest.getProductCode() + "' and discountCode: '" + discountRequest.getDiscountCode() + "'"));
        logger.info("DiscountService:calculateDiscount() received the discount: {}", discount);
        DiscountResponse discountResponse = new DiscountResponse();
        if((LocalDate.now().isAfter(discount.getDiscountStartDate()) && LocalDate.now().isBefore(discount.getDiscountEndDate()))){
            BigDecimal discountedPrice = calculateDiscountedForProduct(discountRequest.getBasePrice(), discount.getDiscountPercentage());
            //when discount needs to be calculated in currency other than base currency (base currency USD)
            if(!Objects.equals(discountRequest.getCurrencyCode(), "") && !BASE_CURRENCY.equals(discountRequest.getCurrencyCode())){
                discountedPrice = discountServiceToCurrencyConverterServiceCommunicator.exchangeCurrency(BASE_CURRENCY, discountRequest.getCurrencyCode(), discountedPrice);
            }
            discountResponse.setProductCode(discountRequest.getProductCode());
            discountResponse.setBasePrice(discountRequest.getBasePrice());
            discountResponse.setDiscountCode(discount.getDiscountCode());
            discountResponse.setDiscountPercentage(discount.getDiscountPercentage());
            discountResponse.setDiscountedPrice(discountedPrice);
            discountResponse.setCurrencyCode(discountRequest.getCurrencyCode());
        } else {
            throw new DiscountExpiredException("Discount has expired");
        }
        logger.info("DiscountService:calculateDiscount() returning discountResponse: {}", discountResponse);
        return discountResponse;
    }

    private BigDecimal calculateDiscountedForProduct(BigDecimal basePrice, int discountPercentage){
        return basePrice.subtract((basePrice.multiply(BigDecimal.valueOf(discountPercentage)).divide(BigDecimal.valueOf(100.0))));
    }

//    public static void main(String[] args) {
//        DiscountRequest discountRequest = new DiscountRequest();
//        discountRequest.setProductCode("ABCD");
//        discountRequest.setDiscountCode("");
//    }

    public static void main(String[] args) {
        DiscountService discountService = new DiscountService(null, null);
        //DiscountService discountService = new DiscountService(null, new DiscountServiceToCurrencyConverterServiceCommunicator(restTemplate));
        BigDecimal a = new BigDecimal("200");
        int b = 5;
        BigDecimal result = discountService.calculateDiscountedForProduct(a, b);
        System.out.println("result = " + result);
    }
}
