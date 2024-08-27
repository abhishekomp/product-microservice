package org.aom.product_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author : Abhishek
 * @since : 2024-08-26, Monday
 **/
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;
    @NotBlank
    private String productName;
    @NotNull
    @NotEmpty
    private String productCode;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal salePrice;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    public Product() {
    }

    public Product(String productName, String productCode, BigDecimal salePrice, ProductCategory productCategory) {
        this.productName = productName;
        this.productCode = productCode;
        this.salePrice = salePrice;
        this.productCategory = productCategory;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && productName.equals(product.productName) && productCode.equals(product.productCode) && salePrice.equals(product.salePrice) && productCategory.equals(product.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, productName, productCode, salePrice, productCategory);
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", salePrice=" + salePrice +
                ", productCategory='" + productCategory + '\'' +
                '}';
    }
}
