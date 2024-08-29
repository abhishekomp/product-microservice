package org.aom.product_service.dto;

import java.math.BigDecimal;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
public class DiscountedPriceResponse {

    private String productCode;
    private String discountCode;
    private BigDecimal discountedPrice;
    private String currencyCode;
    private BigDecimal basePrice;

    private int discountPercentage;

    public DiscountedPriceResponse() {
    }

    public DiscountedPriceResponse(String productCode, String discountCode, BigDecimal discountedPrice, String currencyCode) {
        this.productCode = productCode;
        this.discountCode = discountCode;
        this.discountedPrice = discountedPrice;
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

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return "DiscountedPriceResponse{" +
                "productCode='" + productCode + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", discountedPrice=" + discountedPrice +
                ", currencyCode='" + currencyCode + '\'' +
                ", basePrice=" + basePrice +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
