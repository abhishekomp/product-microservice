package org.aom.discount_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

/**
 * @author : Abhishek
 * @since : 2024-08-28, Wednesday
 **/
@Entity
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String productCode;
    private String discountCode;
    //private String currencyCode;
    private int discountPercentage;
    private LocalDate discountStartDate;
    private LocalDate discountEndDate;

    public Discount() {
    }

    public Discount(String productCode, String discountCode, String currencyCode, int discountPercentage, LocalDate discountStartDate, LocalDate discountEndDate) {
        this.productCode = productCode;
        this.discountCode = discountCode;
        //this.currencyCode = currencyCode;
        this.discountPercentage = discountPercentage;
        this.discountStartDate = discountStartDate;
        this.discountEndDate = discountEndDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

//    public String getCurrencyCode() {
//        return currencyCode;
//    }
//
//    public void setCurrencyCode(String currencyCode) {
//        this.currencyCode = currencyCode;
//    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getDiscountStartDate() {
        return discountStartDate;
    }

    public void setDiscountStartDate(LocalDate discountStartDate) {
        this.discountStartDate = discountStartDate;
    }

    public LocalDate getDiscountEndDate() {
        return discountEndDate;
    }

    public void setDiscountEndDate(LocalDate discountEndDate) {
        this.discountEndDate = discountEndDate;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", discountCode='" + discountCode + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", discountStartDate=" + discountStartDate +
                ", discountEndDate=" + discountEndDate +
                '}';
    }
}
