package org.aom.discount_service.dto;

import java.math.BigDecimal;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
public class DiscountResponse {
    private String productCode;
    private String discountCode;
    private String currencyCode;
    private BigDecimal basePrice;
    private int discountPercentage;
    private BigDecimal discountedPrice;

    public DiscountResponse() {
    }

    public DiscountResponse(String productCode, String discountCode, String currencyCode, BigDecimal basePrice, int discountPercentage, BigDecimal discountedPrice) {
        this.productCode = productCode;
        this.discountCode = discountCode;
        this.currencyCode = currencyCode;
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
        this.discountedPrice = discountedPrice;
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

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String toString() {
        return "DiscountResponse{" +
                "productCode='" + productCode + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", basePrice=" + basePrice +
                ", discountPercentage=" + discountPercentage +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}
