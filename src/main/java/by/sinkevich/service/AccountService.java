package by.sinkevich.service;

import by.sinkevich.model.Account;

import java.util.List;

public interface AccountService {

	long save(Account account);

	Account readById(long id);

	void update(Account account);

	void delete(long id);

	List<Account> findAll();
}
