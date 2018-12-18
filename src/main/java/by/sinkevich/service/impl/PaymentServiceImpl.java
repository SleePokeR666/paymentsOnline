package by.sinkevich.service.impl;

import by.sinkevich.dao.PaymentDao;
import by.sinkevich.model.Payment;
import by.sinkevich.service.PaymentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class PaymentServiceImpl implements PaymentService {

	private static final Logger LOG = LogManager.getLogger();

	private PaymentDao paymentDao;

	@Autowired
	public PaymentServiceImpl(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	@Override
	public long save(Payment payment) {
		payment.setId(paymentDao.save(payment));
		LOG.debug("{} created in database. " + payment);
		return payment.getId();
	}
}
