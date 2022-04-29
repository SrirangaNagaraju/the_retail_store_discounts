package com.nagaraju.retail_store.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagaraju.retail_store.service.Cart;
import com.nagaraju.retail_store.service.ShoppingCart;
import com.nagaraju.retail_store.service.StoreService;
import com.nagaraju.retail_store.model.Discounts;
import com.nagaraju.retail_store.model.Product;
import com.nagaraju.retail_store.model.ProductType;
import com.nagaraju.retail_store.model.Products;
import com.nagaraju.retail_store.model.StoreDiscounts;
import com.nagaraju.retail_store.model.User;
import com.nagaraju.retail_store.model.UserType;

@Component
@RestController
@ComponentScan(basePackages = { "com.nagaraju.retail_store.service" })
public class RestoreController {

	private static final Logger logger = LoggerFactory.getLogger(RestoreController.class);

	@Autowired
	StoreService storeService;

	@GetMapping("/restore/amount")
	public String getAmount() {
		logger.debug("RestoreController inside testGetAmount() method...");
		User employee = new User("Nagaraju", "7829386014", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		Products grocery = new Product("Sugar", 100, ProductType.GROCERY);
		Products jersey = new Product("Jersey", 600, ProductType.SPORTS);
		Products tv = new Product("TV", 5000, ProductType.ELECTRONICS);

		Discounts discount = new StoreDiscounts();
		Cart cart = new ShoppingCart(discount, employee);
		cart.addProducts(grocery, 1);
		cart.addProducts(jersey, 3);
		cart.addProducts(tv, 1);
		return storeService.totalAmount(cart);

	}


}
