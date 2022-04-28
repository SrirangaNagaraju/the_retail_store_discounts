package com.nagaraju.retail_store.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagraju.retail_store.model.Discounts;
import com.nagraju.retail_store.model.ProductType;
import com.nagraju.retail_store.model.Products;
import com.nagraju.retail_store.model.User;
import com.nagraju.retail_store.model.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ShoppingCart implements Cart{
	
	private static final Logger logger=LoggerFactory.getLogger(StoreService.class);
	

    @Getter @Setter
    private Map<Products, Integer> productQuantityMap;
    private Discounts discounts;
    private User user;

    public ShoppingCart(Discounts discounts, User user) {
        productQuantityMap = new LinkedHashMap<>();
        this.discounts = discounts;
        this.user = user;
    }

    @Override
    public void addProducts(Products products, int quantity) {
    	logger.debug("ShoppingCart inside addProducts(products, quantity) method...");
        int storedQuantity = productQuantityMap.getOrDefault(products, 0);
        productQuantityMap.put(products, storedQuantity + quantity);
    }

    @Override
    public int checkUserTypeAndGetDiscounts(User user) {
    	logger.debug("ShoppingCart inside checkUserTypeAndGetDiscounts(User user) method...");
        if(user.getUserType() == UserType.EMPLOYEE) {
        	return 30;
        }else if (user.getUserType() == UserType.AFFILIATE) {
        	return 10;
        }else if (user.getUserType() == UserType.GENERAL && ChronoUnit.YEARS.between(user.getRegisteredDateAndTime(), LocalDateTime.now()) >= 2) {
        	return 5;
        }else{
        	return 0;
        }
    }

    @Override
    public double totalAmount() {
    	logger.debug("ShoppingCart inside totalAmount() method...");
        double total = 0;
        for (Products product : productQuantityMap.keySet()) {
            double productPrice = product.getTotalPrice(productQuantityMap.get(product));
            if(product.getProductType() != ProductType.GROCERY) {
                total +=  (productPrice - (productPrice * checkUserTypeAndGetDiscounts(user) / 100));
            } else {
                total +=  productPrice;
            }

        }

        if(discounts != null) total = discounts.applyDiscount(total);
        
        return total;
    }
}
