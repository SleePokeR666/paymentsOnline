package by.sinkevich.service.impl;

import by.sinkevich.dao.AccountDao;
import by.sinkevich.model.Account;
import by.sinkevich.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountDao accountDao;

	@Autowired
	public AccountServiceImpl(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public long save(Account account) {
		return accountDao.save(account);
	}

	@Override
	public Account readById(long id) {
		return accountDao.readById(id);
	}

	@Override
	public void update(Account account) {
		accountDao.update(account);
	}

	@Override
	public void delete(long id) {
		accountDao.delete(id);
	}

	@Override
	public List<Account> findAll() {
		return accountDao.findAll();
	}
}
