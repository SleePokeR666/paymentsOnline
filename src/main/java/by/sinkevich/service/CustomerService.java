package by.sinkevich.service;

import by.sinkevich.model.Customer;

import java.util.List;

public interface CustomerService {

	long save(Customer customer);

	Customer readById(long id);

	void update(Customer customer);

	void delete(long id);

	List<Customer> findAll();
}
