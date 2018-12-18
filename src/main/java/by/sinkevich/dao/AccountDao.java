package by.sinkevich.dao;

import by.sinkevich.model.Account;

public interface AccountDao {

	long save(Account account);

	Account readById(long id);

	void update(Account account);

}
