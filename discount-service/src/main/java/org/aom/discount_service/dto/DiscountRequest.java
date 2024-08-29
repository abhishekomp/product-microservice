package org.aom.discount_service.dto;

import java.math.BigDecimal;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
public class DiscountRequest {
    private String productCode;
    private String discountCode;
    private String currencyCode;
    private BigDecimal basePrice;

    public DiscountRequest() {
    }

    public DiscountRequest(String productCode, String discountCode, String currencyCode, BigDecimal basePrice) {
        this.productCode = productCode;
        this.discountCode = discountCode;
        this.currencyCode = currencyCode;
        this.basePrice = basePrice;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "DiscountRequest{" +
                "productCode='" + productCode + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
