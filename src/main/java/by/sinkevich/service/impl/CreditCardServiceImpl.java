package by.sinkevich.service.impl;

import by.sinkevich.dao.CreditCardDao;
import by.sinkevich.model.CreditCard;
import by.sinkevich.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	private CreditCardDao creditCardDao;

	@Autowired
	public CreditCardServiceImpl(CreditCardDao creditCardDao) {
		this.creditCardDao = creditCardDao;
	}

	@Override
	public long save(CreditCard creditCard) {
		return creditCardDao.save(creditCard);
	}

	@Override
	public CreditCard readById(long id) {
		return creditCardDao.readById(id);
	}

	@Override
	public void update(CreditCard creditCard) {
		creditCardDao.update(creditCard);
	}

	@Override
	public void delete(long id) {
		creditCardDao.delete(id);
	}

	@Override
	public List<CreditCard> findAll() {
		return creditCardDao.findAll();
	}
}
