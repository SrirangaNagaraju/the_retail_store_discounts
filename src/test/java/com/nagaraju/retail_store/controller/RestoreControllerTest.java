package com.nagaraju.retail_store.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

import com.nagaraju.retail_store.service.Cart;
import com.nagaraju.retail_store.service.ShoppingCart;
import com.nagaraju.retail_store.service.StoreService;
import com.nagraju.retail_store.model.Discounts;
import com.nagraju.retail_store.model.Product;
import com.nagraju.retail_store.model.ProductType;
import com.nagraju.retail_store.model.Products;
import com.nagraju.retail_store.model.StoreDiscounts;
import com.nagraju.retail_store.model.User;
import com.nagraju.retail_store.model.UserType;

@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackages = { "com.nagaraju.retail_store.controller", "com.nagaraju.retail_store.service",
		"com.nagraju.retail_store.model" })
public class RestoreControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(RestoreControllerTest.class);

	@InjectMocks
	RestoreController restoreController;

	@Mock
	StoreService storeService;
	@Mock
	StoreDiscounts storeDiscounts;

	@Mock
	Discounts discounts;

	@Mock
	ShoppingCart shoppingCart;

	@Mock
	Cart cart;
	@Mock
	Products groceryItem;
	@Mock
	Products otherItem;
	@Mock
	User employee;
	@Mock
	User affiliate;
	@Mock
	User simpleUser;
	@Mock
	User simpleUserWith2Years;
	@Mock
	Discounts discount;

	@Mock
	Products jersey;

	@Mock
	Products tv;

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
	public void testGetAmount() {
		logger.debug("RestoreControllerTest inside testGetAmount() method...");
		employee = new User("Nagaraju", "7829386014", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		discount = new StoreDiscounts();
		Cart cart = new ShoppingCart(discount, employee);
		cart.addProducts(groceryItem, 1);
		cart.addProducts(jersey, 3);
		cart.addProducts(tv, 1);
		when(storeService.totalAmount(any())).thenReturn("Total Amount To Be Paid: " + 4620.0);
		String actualValue = restoreController.getAmount();
		Assert.assertEquals("Total Amount To Be Paid: " + 4620.0, actualValue);

	}

}
