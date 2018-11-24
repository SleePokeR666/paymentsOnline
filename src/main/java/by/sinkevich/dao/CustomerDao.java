package by.sinkevich.dao;

import by.sinkevich.model.Customer;

import java.util.List;

public interface CustomerDao {

	long save(Customer customer);

	Customer readById(long id);

	void update(Customer customer);

	void delete(long id);

	List<Customer> findAll();

}
