package com.nagaraju.retail_store.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagaraju.retail_store.model.Products;

public class Receipt implements Bill{
	private static final Logger logger = LoggerFactory.getLogger(Receipt.class);
    @Override
    public void printBill(Cart cart) {
    	logger.debug("Receipt inside printBill(cart) method...");
        for(Products products: ((ShoppingCart)cart).getProductQuantityMap().keySet()) {
            System.out.println(products.getProductName() + " :: " + products.getProductType() + " :: " + products.getProductUnitPrice() + " :: "+ ((ShoppingCart)cart).getProductQuantityMap().get(products) + " :: " + products.getTotalPrice(((ShoppingCart)cart).getProductQuantityMap().get(products)));
        }

        System.out.println("Total Amount After Discount :: " + cart.totalAmount());

    }
}
