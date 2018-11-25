package by.sinkevich.dao;

import by.sinkevich.model.Account;

import java.util.List;

public interface AccountDao {

	long save(Account account);

	Account readById(long id);

	void update(Account account);

	void delete(long id);

	List<Account> findAll();
}
