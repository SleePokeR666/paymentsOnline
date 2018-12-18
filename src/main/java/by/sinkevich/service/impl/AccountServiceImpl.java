package by.sinkevich.service.impl;

import by.sinkevich.dao.AccountDao;
import by.sinkevich.model.Account;
import by.sinkevich.service.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class AccountServiceImpl implements AccountService {

	private static final Logger LOG = LogManager.getLogger();

	private AccountDao accountDao;

	@Autowired
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
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
}
