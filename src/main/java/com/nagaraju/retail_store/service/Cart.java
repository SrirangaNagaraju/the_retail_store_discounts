package com.nagaraju.retail_store.service;

import com.nagaraju.retail_store.model.Products;
import com.nagaraju.retail_store.model.User;

public interface Cart {
    void addProducts(Products products, int quantity);
    int checkUserTypeAndGetDiscounts(User user);
    double totalAmount();
}
