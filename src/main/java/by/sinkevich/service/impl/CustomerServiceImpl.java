package by.sinkevich.service.impl;

import by.sinkevich.dao.CustomerDao;
import by.sinkevich.model.Account;
import by.sinkevich.model.CreditCard;
import by.sinkevich.model.Customer;
import by.sinkevich.service.AccountService;
import by.sinkevich.service.CreditCardService;
import by.sinkevich.service.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOG = LogManager.getLogger();

	private final CustomerDao customerDao;

	private final AccountService accountService;

	private final CreditCardService creditCardService;

	@Autowired
	public CustomerServiceImpl(CustomerDao customerDao, AccountService accountService, CreditCardService creditCardService) {
		this.customerDao = customerDao;
		this.accountService = accountService;
		this.creditCardService = creditCardService;
	}

	@Override
	public long save(Customer customer) {
		long customerId = customerDao.save(customer);
		customer.setId(customerId);
		LOG.trace(customer + "created in database");

		Account account = new Account();
		account.setActive(true);
		account.setBalance(.0d);
		account.setId(accountService.save(account));

		CreditCard creditCard = new CreditCard();
		creditCard.setNumber((int) (Math.random() * Integer.MAX_VALUE));
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
		if (customer.getPassword().equals(password)) {
			return customer;
		}
		return null;
	}
}
