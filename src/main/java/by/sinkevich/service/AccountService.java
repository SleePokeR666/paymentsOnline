package by.sinkevich.service;

import by.sinkevich.model.Account;

public interface AccountService {

	long save(Account account);

	Account readById(long id);

	void update(Account account);

	void block(Account account);

	void blockById(long id);

	void unBlock(long id);

	boolean deposit(Account account, Double amount);

}
