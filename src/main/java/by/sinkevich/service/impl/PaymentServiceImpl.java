package by.sinkevich.service.impl;

import by.sinkevich.dao.PaymentDao;
import by.sinkevich.model.Payment;
import by.sinkevich.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class PaymentServiceImpl implements PaymentService {

	private PaymentDao paymentDao;

	@Autowired
	public PaymentServiceImpl(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	@Override
	public long save(Payment payment) {
		return paymentDao.save(payment);
	}
}
