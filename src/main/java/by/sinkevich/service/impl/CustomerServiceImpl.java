package by.sinkevich.service.impl;

import by.sinkevich.dao.CustomerDao;
import by.sinkevich.model.Account;
import by.sinkevich.model.CreditCard;
import by.sinkevich.model.Customer;
import by.sinkevich.service.AccountService;
import by.sinkevich.service.CreditCardService;
import by.sinkevich.service.CustomerService;
import by.sinkevich.util.CreditCardNumberGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOG = LogManager.getLogger();

	private CustomerDao customerDao;

	private AccountService accountService;

	private CreditCardService creditCardService;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public CustomerServiceImpl(CustomerDao customerDao, AccountService accountService, CreditCardService creditCardService) {
		this.customerDao = customerDao;
		this.accountService = accountService;
		this.creditCardService = creditCardService;
	}

	@Override
	public long save(Customer customer) {
		encodeCustomerPassword(customer);
		long customerId = customerDao.save(customer);
		customer.setId(customerId);
		LOG.trace("{} created in database. " + customer);

		Account account = new Account();
		account.setActive(true);
		account.setBalance(.0d);
		accountService.save(account);

		CreditCard creditCard = new CreditCard();
		creditCard.setNumber(CreditCardNumberGenerator.generateCreditCardNumber());
		creditCard.setAccount(account);
		creditCard.setCustomer(customer);
		creditCardService.save(creditCard);
		return customerId;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
	public Customer readById(long id) {
		return customerDao.readById(id);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public void delete(long id) {
		customerDao.delete(id);
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public Customer login(String login, String password) {
		Customer customer = customerDao.readByLogin(login);
		if (!passwordEncoder.matches(password, customer.getPassword())) {
			throw new IllegalArgumentException("Неправильно введён пароль. Попробуйте ещё раз!");
		}
		return customer;
	}

	private void encodeCustomerPassword(Customer customer) {
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
