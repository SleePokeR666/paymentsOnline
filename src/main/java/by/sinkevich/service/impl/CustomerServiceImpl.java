package by.sinkevich.service.impl;

import by.sinkevich.dao.CustomerDao;
import by.sinkevich.model.Customer;
import by.sinkevich.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerDao customerDao;

	@Autowired
	public CustomerServiceImpl(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
