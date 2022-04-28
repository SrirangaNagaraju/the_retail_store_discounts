package com.nagaraju.retail_store.service;

import java.time.LocalDateTime;
import java.time.Month;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;

import com.nagraju.retail_store.model.Discounts;
import com.nagraju.retail_store.model.Product;
import com.nagraju.retail_store.model.ProductType;
import com.nagraju.retail_store.model.Products;
import com.nagraju.retail_store.model.StoreDiscounts;
import com.nagraju.retail_store.model.User;
import com.nagraju.retail_store.model.UserType;

@ExtendWith(MockitoExtension.class)
@ComponentScan(basePackages = { "com.nagaraju.retail_store.service", "com.nagraju.retail_store.model" })
public class ShoppingCartTest {
	private static final Logger logger = LoggerFactory.getLogger(ShoppingCartTest.class);

	@Mock
	ShoppingCart shoppingCart;

	@Mock
	StoreService storeService;

	@Mock
	StoreDiscounts storeDiscounts;

	@Mock
	Discounts discounts;

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

	@Before
	public void setUp() {
		employee = new User("nagaraju", "7795029445", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		affiliate = new User("Rutvik", "7795029445", LocalDateTime.now(), LocalDateTime.now(), UserType.AFFILIATE);
		simpleUser = new User("Lasya", "7795029445", LocalDateTime.now(), LocalDateTime.now(), UserType.GENERAL);
		simpleUserWith2Years = new User("Raju", "7795029445", LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30),
				LocalDateTime.now(), UserType.GENERAL);

		groceryItem = new Product("Soap", 20, ProductType.GROCERY);
		otherItem = new Product("TV", 5000, ProductType.ELECTRONICS);// 222
		discounts = new StoreDiscounts();
	}

	@Test
	public void testCheckUserTypeAndGetDiscountsWithEmployee() {
		logger.debug("ShoppingCartTest inside test_employeeWithGrocery() method...");
		employee = new User("Nagaraju", "7829386014", LocalDateTime.now(), LocalDateTime.now(), UserType.EMPLOYEE);
		discount = new StoreDiscounts();
		Cart cart = new ShoppingCart(discount, employee);
		cart.addProducts(groceryItem, 4);
		int actualValue = cart.checkUserTypeAndGetDiscounts(employee);
		Assert.assertEquals(30, actualValue);
	}

	@Test
	public void testCheckUserTypeAndGetDiscountsWithAffiliate() {
		logger.debug("ShoppingCartTest inside test_employeeWithGrocery() method...");
		affiliate = new User("Nagaraju", "7829386014", LocalDateTime.now(), LocalDateTime.now(), UserType.AFFILIATE);
		discount = new StoreDiscounts();
		Cart cart = new ShoppingCart(discount, affiliate);
		cart.addProducts(otherItem, 4);
		int actualValue = cart.checkUserTypeAndGetDiscounts(affiliate);
		Assert.assertEquals(10, actualValue);
	}

	@Test
	public void testCheckUserTypeAndGetDiscountsWithGeneral() {
		logger.debug("ShoppingCartTest inside test_employeeWithGrocery() method...");
		simpleUser = new User("Nagaraju", "7829386014", LocalDateTime.now(), LocalDateTime.now(), UserType.GENERAL);
		discount = new StoreDiscounts();
		Cart cart = new ShoppingCart(discount, simpleUser);
		cart.addProducts(otherItem, 4);
		int actualValue = cart.checkUserTypeAndGetDiscounts(simpleUser);
		Assert.assertEquals(0, actualValue);
	}

}
