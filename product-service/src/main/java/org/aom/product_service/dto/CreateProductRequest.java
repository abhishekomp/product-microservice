package org.aom.product_service.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.aom.product_service.model.ProductCategory;
import org.aom.product_service.validation.ValueOfEnum;

import java.math.BigDecimal;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
public class CreateProductRequest {
    @NotBlank
    private String productName;
    @NotBlank
    private String productCode;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salePrice;

    @ValueOfEnum(enumClass = ProductCategory.class)
    private String productCategory;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String productName, String productCode, BigDecimal salePrice, String productCategory) {
        this.productName = productName;
        this.productCode = productCode;
        this.salePrice = salePrice;
        this.productCategory = productCategory;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", salePrice=" + salePrice +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}
