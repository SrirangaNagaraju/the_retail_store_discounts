package com.nagaraju.retail_store.main;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.nagaraju.retail_store.service.Bill;
import com.nagaraju.retail_store.service.Cart;
import com.nagaraju.retail_store.service.Receipt;
import com.nagaraju.retail_store.service.ShoppingCart;
import com.nagaraju.retail_store.model.Discounts;
import com.nagaraju.retail_store.model.Product;
import com.nagaraju.retail_store.model.ProductType;
import com.nagaraju.retail_store.model.Products;
import com.nagaraju.retail_store.model.StoreDiscounts;
import com.nagaraju.retail_store.model.User;
import com.nagaraju.retail_store.model.UserType;

@SpringBootApplication
@ComponentScan(basePackages = {"com.nagaraju.retail_store.controller"})
public class TheRetailStoreDiscountsApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(TheRetailStoreDiscountsApplication.class, args);

		User user = new User("Nagaraju", "7829386014", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		Products grocery = new Product("Atta", 200, ProductType.GROCERY);
		Products cloth = new Product("T-Shirt", 800, ProductType.CLOTHING);

		Discounts discounts = new StoreDiscounts();
		Cart cart = new ShoppingCart(discounts, user);
		cart.addProducts(grocery, 2);
		cart.addProducts(cloth, 1);

		Bill bill = new Receipt();
		bill.printBill(cart);

	}

}
