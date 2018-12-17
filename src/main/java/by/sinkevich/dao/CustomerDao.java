package by.sinkevich.dao;

import by.sinkevich.model.Customer;

import java.util.List;

public interface CustomerDao {

	long save(Customer customer);

	Customer readByIdLazy(long id);

	Customer readById(long id);

	void update(Customer customer);

	void delete(long id);

	List<Customer> finaAllLazy();

	List<Customer> findAll();

	Customer readByLogin(String login);
}
