package com.nagraju.retail_store.model;

public interface Products {

    String getProductName();
    ProductType getProductType();
    double getProductUnitPrice();
    double getTotalPrice(int quantity);
}
