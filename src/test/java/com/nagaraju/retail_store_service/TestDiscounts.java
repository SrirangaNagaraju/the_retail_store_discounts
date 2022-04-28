package com.nagaraju.retail_store_service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nagaraju.retail_store.controller.RestoreControllerTest;
import com.nagaraju.retail_store.service.Cart;
import com.nagaraju.retail_store.service.ShoppingCart;
import com.nagraju.retail_store.model.Discounts;
import com.nagraju.retail_store.model.Product;
import com.nagraju.retail_store.model.ProductType;
import com.nagraju.retail_store.model.Products;
import com.nagraju.retail_store.model.StoreDiscounts;
import com.nagraju.retail_store.model.User;
import com.nagraju.retail_store.model.UserType;

public class TestDiscounts {

	private static final Logger logger = LoggerFactory.getLogger(RestoreControllerTest.class);

	private Products groceryItem;
	private Products otherItem;
	private User employee;
	private User affiliate;
	private User simpleUser;
	private User simpleUserWith2Years;
	private Discounts discounts;

	@Before
	public void setUp() {
		employee = new User("nagaraju", "7795029445", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		affiliate = new User("Rutvik", "7795029445", LocalDateTime.now(), LocalDateTime.now(), UserType.AFFILIATE);
		simpleUser = new User("Lasya", "7795029445", LocalDateTime.now(), LocalDateTime.now(), UserType.GENERAL);
		simpleUserWith2Years = new User("Raju", "7795029445", LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30),
				LocalDateTime.now(), UserType.GENERAL);

		groceryItem = new Product("Soap", 20, ProductType.GROCERY);
		otherItem = new Product("TV", 222, ProductType.ELECTRONICS);
		discounts = new StoreDiscounts();
	}

	@Test
	public void test_employeeWithGrocery() {
		logger.debug("TestDiscounts inside test_employeeWithGrocery() method...");
		Cart cart = new ShoppingCart(discounts, employee);
		cart.addProducts(groceryItem, 4);
		// No discount because of grocery item
		assertEquals(80, cart.totalAmount(), 0.01);

	}

	@Test
	public void test_employeeWithOtherItem() {
		Cart cart = new ShoppingCart(discounts, employee);
		cart.addProducts(otherItem, 4);
		/*
		 * 30% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 222 * 4 = 888 After 30% discount = 621.6 After 30 dollars
		 * off due to price over $600 = 591.6
		 */
		assertEquals(4620.0, cart.totalAmount(), 0.01);

	}

	@Test
	public void test_affiliateWithGrocery() {
		Cart cart = new ShoppingCart(discounts, affiliate);
		cart.addProducts(groceryItem, 4);
		// No discount because of grocery item
		assertEquals(80, cart.totalAmount(), 0.01);

	}

	@Test
	public void test_affiliateWithOtherItem() {
		Cart cart = new ShoppingCart(discounts, affiliate);
		cart.addProducts(otherItem, 4);
		assertEquals(764.2, cart.totalAmount(), 0.01);

	}

	@Test
	public void test_simpleUserWithGrocery() {
		Cart cart = new ShoppingCart(discounts, simpleUser);
		cart.addProducts(groceryItem, 4);
		// No discount because of grocery item
		assertEquals(80, cart.totalAmount(), 0.01);

	}

	@Test
	public void test_simpleUserWithOtherItem() {
		Cart cart = new ShoppingCart(discounts, simpleUser);
		cart.addProducts(otherItem, 4);
		/*
		 * Total 222 * 4 = 888 No percentage discount because user is a customer for
		 * less than 2 years After 40 dollars off due to price over $800 = 848
		 */
		assertEquals(848, cart.totalAmount(), 0.01);
	}

	@Test
	public void test_simpleUserWith2YearsWithGrocery() {
		Cart cart = new ShoppingCart(discounts, simpleUserWith2Years);
		cart.addProducts(groceryItem, 4);
		// No discount because of grocery item
		assertEquals(80, cart.totalAmount(), 0.01);

	}

	@Test
	public void test_simpleUserWith2YearsWithOtherItem() {
		Cart cart = new ShoppingCart(discounts, simpleUserWith2Years);
		cart.addProducts(otherItem, 4);
		/*
		 * 5% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 222 * 4 = 888 After 5% discount = 843.6 After 40 dollars off
		 * due to price over $800 =803.6
		 */
		assertEquals(803.6, cart.totalAmount(), 0.01);

	}

	@Test
	public void test_employeeWithGroceryAndOtherItem() {
		Cart cart = new ShoppingCart(discounts, employee);
		cart.addProducts(groceryItem, 4);
		cart.addProducts(otherItem, 4);
		/*
		 * Total (20 * 4) + (222 * 4) = 968 No discount on grocery items = 968 still
		 * After 30% discount on 4 other items totalling 888 = 701.6 After 35 dollars
		 * off due to price over $700 =666.6
		 */
		assertEquals(666.6, cart.totalAmount(), 0.01);

	}
}
