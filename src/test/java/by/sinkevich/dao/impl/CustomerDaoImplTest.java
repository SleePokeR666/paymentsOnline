package by.sinkevich.dao.impl;

import by.sinkevich.config.SpringConfig;
import by.sinkevich.dao.CustomerDao;
import by.sinkevich.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = {SpringConfig.class})
@TestPropertySource(properties = {"datasource.url = jdbc:mysql://localhost:3306/test_payments"})
@Sql(scripts = {
		"classpath:scripts/create_tables.sql",
		"classpath:scripts/insert_data.sql"
})
@Sql(scripts = "classpath:scripts/drop_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CustomerDaoImplTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private CustomerDao customerDao;

	@Test
	public void saveTest() {
		Customer expected = new Customer();
		expected.setName("Denis");
		expected.setPassword("Password2");
		expected.setLogin("Login2");

		expected.setId(customerDao.save(expected));

		Customer actual = customerDao.readByIdLazy(expected.getId());

		assertEquals(actual, expected);
	}

	@Test
	public void readByIdTest() {
		Customer expected = new Customer();
		expected.setId(1);
		expected.setName("Денис");
		expected.setPassword("Password");
		expected.setLogin("Login");

		Customer actual = customerDao.readByIdLazy(1);

		assertEquals(actual, expected);
	}

	@Test(dependsOnMethods = "readByIdTest")
	public void updateTest() {
		Customer expected = new Customer();
		expected.setId(1);
		expected.setName("ChangedName");
		expected.setPassword("ChangedPassword");
		expected.setLogin("ChangedLogin");

		customerDao.update(expected);

		Customer actual = customerDao.readByIdLazy(1);
		assertEquals(actual, expected);
	}

	@Test
	public void findAllTest() {
		List<Customer> expected = new ArrayList<>();
		Customer expectedCustomer = new Customer();
		expectedCustomer.setId(1);
		expectedCustomer.setName("Денис");
		expectedCustomer.setPassword("Password");
		expectedCustomer.setLogin("Login");
		expected.add(expectedCustomer);

		List<Customer> actual = customerDao.findAll();

		assertEquals(actual, expected);
	}
}
