package com.nagraju.retail_store.model;

public interface Discounts {
    DiscountType getDiscountType();
    double applyDiscount(double amount);
}
