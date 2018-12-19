package by.sinkevich.service.impl;

import by.sinkevich.dao.AccountDao;
import by.sinkevich.model.Account;
import by.sinkevich.model.Payment;
import by.sinkevich.service.AccountService;
import by.sinkevich.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class AccountServiceImpl implements AccountService {

	private static final Logger LOG = LogManager.getLogger();

	private AccountDao accountDao;

	private PaymentService paymentService;

	@Autowired
	public AccountServiceImpl(AccountDao accountDao, PaymentService paymentService) {
		this.accountDao = accountDao;
		this.paymentService = paymentService;
	}

	@Override
	public long save(Account account) {
		account.setId(accountDao.save(account));
		LOG.debug("{} created in database. " + account);
		return account.getId();
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
	public Account readById(long id) {
		return accountDao.readById(id);
	}

	@Override
	public void update(Account account) {
		accountDao.update(account);
		LOG.debug("{} updated in database. " + account);
	}

	@Override
	public void block(Account account) {
		account.setIsActive(false);
		accountDao.update(account);
		LOG.debug("{} blocked. ", account);
	}

	@Override
	public void blockById(long id) {
		Account account = readById(id);
		block(account);
	}

	@Override
	public void unBlock(long id) {
		Account account = readById(id);
		account.setIsActive(true);
		update(account);
		LOG.debug("{} unblocked. ", account);
	}

	@Override
	public boolean deposit(Account account, Double amount) {
		Payment payment = new Payment();
		payment.setAmount(amount);
		payment.setDate(new Date());
		payment.setToAccount(account);
		payment.setStatus("completed");
		paymentService.save(payment);
		account.setBalance(account.getBalance() + amount);
		update(account);
		LOG.debug("Deposit successfully completed. Account {}, amount {} ", account, amount);
		return true;
	}
}
