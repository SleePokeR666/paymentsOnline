package by.sinkevich.dao;

import by.sinkevich.model.Customer;

import java.util.List;

public interface CustomerDao {

	List<Customer> findAll();

}
