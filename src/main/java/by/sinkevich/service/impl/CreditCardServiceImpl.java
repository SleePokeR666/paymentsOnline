package by.sinkevich.service.impl;

import by.sinkevich.dao.CreditCardDao;
import by.sinkevich.model.CreditCard;
import by.sinkevich.service.CreditCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class CreditCardServiceImpl implements CreditCardService {

	private static final Logger LOG = LogManager.getLogger();

	private CreditCardDao creditCardDao;

	@Autowired
	public CreditCardServiceImpl(CreditCardDao creditCardDao) {
		this.creditCardDao = creditCardDao;
	}

	@Override
	public long save(CreditCard creditCard) {
		creditCard.setId(creditCardDao.save(creditCard));
		LOG.trace("{} created in database. " + creditCard);
		return creditCard.getId();
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
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
	@Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
	public List<CreditCard> findAll() {
		return creditCardDao.findAll();
	}
}
