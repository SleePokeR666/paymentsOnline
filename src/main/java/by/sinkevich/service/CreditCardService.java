package by.sinkevich.service;

import by.sinkevich.model.CreditCard;

import java.util.List;

public interface CreditCardService {

	long save(CreditCard creditCard);

	CreditCard readById(long id);

	void update(CreditCard creditCard);

	void delete(long id);

	List<CreditCard> findAll();
}
