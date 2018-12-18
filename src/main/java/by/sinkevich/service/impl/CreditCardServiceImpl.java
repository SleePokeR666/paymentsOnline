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
		LOG.debug("{} created in database. " + creditCard);
		return creditCard.getId();
	}
}
