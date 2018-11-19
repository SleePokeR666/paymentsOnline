package by.sinkevich.mapper;

import by.sinkevich.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
		Customer customer = new Customer();
		customer.setId(resultSet.getLong("id"));
		customer.setName(resultSet.getString("name"));
		customer.setLogin(resultSet.getString("login"));
		customer.setPassword(resultSet.getString("password"));
		return customer;
	}
}
