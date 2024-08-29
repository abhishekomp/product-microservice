package org.aom.product_service.dto;

import java.math.BigDecimal;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
public class DiscountedPriceRequest {
    private String productCode;
    private String discountCode;
    private String currencyCode;

    private BigDecimal basePrice;

    public DiscountedPriceRequest() {
    }

    public DiscountedPriceRequest(String productCode, String discountCode) {
        this.productCode = productCode;
        this.discountCode = discountCode;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    @Override
    public String toString() {
        return "DiscountedPriceRequest{" +
                "productCode='" + productCode + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
