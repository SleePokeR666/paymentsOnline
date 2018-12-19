package by.sinkevich.service;

import by.sinkevich.model.Customer;

import java.util.List;

public interface CustomerService {

	long save(Customer customer);

	List<Customer> findAll();

	Customer readByLogin(String login);

	Customer authentication(String login, String password);
}
