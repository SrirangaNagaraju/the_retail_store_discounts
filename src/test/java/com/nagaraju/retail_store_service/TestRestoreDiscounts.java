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

public class TestRestoreDiscounts {

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
		simpleUserWith2Years = new User("Raju", "7795029445", LocalDateTime.of(2018, Month.MARCH, 22, 18, 15),
				LocalDateTime.now(), UserType.GENERAL);

		groceryItem = new Product("Soap", 40, ProductType.GROCERY);
		otherItem = new Product("Mobile", 5000, ProductType.ELECTRONICS);
		discounts = new StoreDiscounts();
	}

	@Test
	public void testEmployeeWithGrocery() {
		logger.debug("TestDiscounts inside test_employeeWithGrocery() method...");
		Cart cart = new ShoppingCart(discounts, employee);
		cart.addProducts(groceryItem, 4);
		 //The percentage based discounts do not apply on groceries. 4*40=160.
		assertEquals(160, cart.totalAmount(), 0.01);

	}

	@Test
	public void testEmployeeWithOtherItem() {
		Cart cart = new ShoppingCart(discounts, employee);
		cart.addProducts(otherItem, 4);
		cart.addProducts(groceryItem, 1);
		
		 /*
         *  30% discount then 5 dollars off of each 100 dollars of total price because of other item
         *  Total 5000 * 4 = 20000
         *  After 30% discount = 14000.0
         *  every	$100	on	the	bill,	there	would	be	a	$	5	discount
         *  After 140*5 dollars off due to price over $700.0 = 13300.0
         */
	
		assertEquals(13300.0, cart.totalAmount(), 0.01);

	}

	@Test
	public void testAffiliateWithGrocery() {
		Cart cart = new ShoppingCart(discounts, affiliate);
		cart.addProducts(groceryItem, 5);
		//The percentage based discounts do not apply on groceries. 4*40=160
		assertEquals(160, cart.totalAmount(), 0.01);

	}

	@Test
	public void testAffiliateWithOtherItem() {
		Cart cart = new ShoppingCart(discounts, affiliate);
		cart.addProducts(otherItem, 4);
		 /*
         *  30% discount then 5 dollars off of each 100 dollars of total price because of other item
         *  Total 5000 * 4 = 20000
         *  After 30% discount = 14000.0
         *  every	$100	on	the	bill,	there	would	be	a	$	5	discount
         *  After 140*5 dollars off due to price over $700.0 = 13300.0
         */
		assertEquals(13300.0, cart.totalAmount(), 0.01);

	}

	@Test
	public void testSimpleUserWithGrocery() {
		Cart cart = new ShoppingCart(discounts, simpleUser);
		cart.addProducts(groceryItem, 2);
		// The percentage based discounts do not apply on groceries.2*40=80
		assertEquals(80, cart.totalAmount(), 0.01);

	}

	@Test
	public void testSimpleUserWithOtherItem() {
		Cart cart = new ShoppingCart(discounts, simpleUser);
		cart.addProducts(otherItem, 4);
		/*
         *  Total 5000 * 4 = 20000
         *  No percentage discount because user is a customer for less than 2 years
         *  A	user can	get	only	one	of	the	percentage	based	discounts	on	a	bill.
         *  After 950 dollars off due to price = 19050.0 
         */
		assertEquals(19050.0, cart.totalAmount(), 0.01);
	}

	@Test
	public void testSimpleUserWith2YearsWithGrocery() {
		Cart cart = new ShoppingCart(discounts, simpleUserWith2Years);
		cart.addProducts(groceryItem, 6);
		// The percentage based discounts do not apply on groceries.6*40=240
		assertEquals(240, cart.totalAmount(), 0.01);

	}

	@Test
	public void testSimpleUserWith2YearsWithOtherItem() {
		Cart cart = new ShoppingCart(discounts, simpleUserWith2Years);
		cart.addProducts(otherItem, 4);
		/*
		 * 5% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 5000 * 4 = 20000 After 5% discount = 19000 After 950 dollars off
		 * due to price over $19000 =18050.0
		 */
		assertEquals(18050.0, cart.totalAmount(), 0.01);

	}

	@Test
	public void testEmployeeWithGroceryAndOtherItem() {
		Cart cart = new ShoppingCart(discounts, employee);
		cart.addProducts(groceryItem, 2);
		cart.addProducts(otherItem, 2);
		/*
		 * Total (40 * 2) + (5000 * 2) = 80* 10000=800000 No discount on grocery items = 968 still
		 * After 30% discount on 2 other items total 3000 discount(10000-3000)  = 7000.0 After 350 dollars(5%discount)
		 * off due to price over $7000-350 =6650.0
		 */
		assertEquals(6650.0, cart.totalAmount(), 0.01);

	}
}
