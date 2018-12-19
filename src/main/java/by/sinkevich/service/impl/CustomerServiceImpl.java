package by.sinkevich.service.impl;

import by.sinkevich.dao.CustomerDao;
import by.sinkevich.exception.AuthenticationException;
import by.sinkevich.exception.RegistrationException;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
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
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public long save(Customer customer) {
		if (readByLogin(customer.getLogin()) != null) {
			String message = "Пользователь с указанным именем уже существует. " +
					"Пожалуйста, выберите другой логин.";
			LOG.error("Failed to create customer with login {}. Already exists.", customer.getLogin());
			throw new RegistrationException(message);
		}
		encodeCustomerPassword(customer);
		long customerId = customerDao.save(customer);
		customer.setId(customerId);
		LOG.debug("{} created in database. " + customer);

		Account account = new Account();
		account.setIsActive(true);
		account.setBalance(.0d);
		accountService.save(account);

		CreditCard creditCard = new CreditCard();
		creditCard.setNumber(CreditCardNumberGenerator.generateCreditCardNumber());
		creditCard.setAccount(account);
		creditCard.setCustomer(customer);
		creditCardService.save(creditCard);

		List<CreditCard> creditCards = new ArrayList<>();
		creditCards.add(creditCard);
		customer.setCreditCards(creditCards);
		return customerId;
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public Customer readByLogin(String login) {
		return customerDao.readByLogin(login);
	}

	@Override
	public Customer authentication(String login, String password) {
		Customer customer = customerDao.readByLogin(login);
		if (customer == null) {
			LOG.error("Failed to authenticate customer with login {}. Doesn't exists.", login);
			throw new AuthenticationException("Пользователя с заданным логином не существует.");
		}
		if (!passwordEncoder.matches(password, customer.getPassword())) {
			LOG.error("Failed to authenticate customer with login {}. Wrong password.", login);
			throw new AuthenticationException("Неправильно введён пароль! Попробуйте ещё раз.");
		}
		return customerDao.readById(customer.getId());
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
