package by.sinkevich.dao.impl;

import by.sinkevich.config.SpringConfig;
import by.sinkevich.dao.CustomerDao;
import by.sinkevich.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = {SpringConfig.class})
@TestPropertySource(properties = {"datasource.url = jdbc:mysql://localhost:3306/test_payments"})
public class CustomerDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private CustomerDao customerDao;

	@Test
	public void saveTest() {
		Customer expected = new Customer();
		expected.setName("Denis");
		expected.setPassword("Password2");
		expected.setLogin("Login2");
		expected.setIsAdmin(true);

		expected.setId(customerDao.save(expected));

		Customer actual = customerDao.readByIdLazy(expected.getId());

		assertEquals(actual, expected);
	}

	@Test
	public void readByIdLazyTest() {
		Customer expected = new Customer();
		expected.setId(1);
		expected.setName("FullName");
		expected.setPassword("Password");
		expected.setLogin("Login");
		expected.setIsAdmin(true);

		Customer actual = customerDao.readByIdLazy(1);

		assertEquals(actual, expected);
	}

	@Test(dependsOnMethods = "readByIdLazyTest")
	public void updateTest() {
		Customer expected = new Customer();
		expected.setId(1);
		expected.setName("ChangedName");
		expected.setPassword("ChangedPassword");
		expected.setLogin("ChangedLogin");
		expected.setIsAdmin(true);

		customerDao.update(expected);

		Customer actual = customerDao.readByIdLazy(1);
		assertEquals(actual, expected);
	}

	@Test
	public void findAllLazyTest() {
		List<Customer> expected = new ArrayList<>();
		Customer expectedCustomer = new Customer();
		expectedCustomer.setId(1);
		expectedCustomer.setName("FullName");
		expectedCustomer.setPassword("Password");
		expectedCustomer.setLogin("Login");
		expectedCustomer.setIsAdmin(true);
		expected.add(expectedCustomer);

		List<Customer> actual = customerDao.finaAllLazy();

		assertEquals(actual, expected);
	}

	@BeforeClass
	public void prepareDatabase() {
		executeSqlScript("classpath:scripts/create_tables.sql", false);
		executeSqlScript("classpath:scripts/insert_data.sql", false);
	}

	@AfterClass
	public void clearDatabase() {
		executeSqlScript("classpath:scripts/drop_tables.sql", false);
	}
}
