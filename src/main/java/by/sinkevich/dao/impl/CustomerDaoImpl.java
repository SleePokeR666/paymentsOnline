package by.sinkevich.dao.impl;

import by.sinkevich.dao.CustomerDao;
import by.sinkevich.mapper.CustomerMapper;
import by.sinkevich.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	private JdbcTemplate jdbcTemplate;

	private CustomerMapper customerMapper;

	@Autowired
	public CustomerDaoImpl(JdbcTemplate jdbcTemplate, CustomerMapper customerMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.customerMapper = customerMapper;
	}

	@Override
	public long save(Customer customer) {
		String sql = "INSERT INTO customer (name, login, password) VALUES (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = con -> {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getLogin());
			ps.setString(3, customer.getPassword());
			return ps;
		};
		jdbcTemplate.update(psc, keyHolder);
		Number key = keyHolder.getKey();
		return key != null ? key.longValue() : 0;
	}

	@Override
	public Customer readById(long id) {
		String sql = "SELECT * FROM customer WHERE id = ?";
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		List<Customer> customers = jdbcTemplate.query(sql, pss, customerMapper);
		return customers.get(0);
	}

	@Override
	public void update(Customer customer) {
		String sql = "UPDATE customer SET name = ?, login = ?, password = ? WHERE id = ?";
		PreparedStatementSetter pss = ps -> {
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getLogin());
			ps.setString(3, customer.getPassword());
			ps.setLong(4, customer.getId());
		};
		jdbcTemplate.update(sql, pss);
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM customer WHERE id = ?";
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		jdbcTemplate.update(sql, pss);
	}

	@Override
	public List<Customer> findAll() {
		String sql = "SELECT * FROM customer";
		return jdbcTemplate.query(sql, customerMapper);
	}

	@Override
	public Customer readByLogin(String login) {
		String sql = "SELECT * FROM customer WHERE login = ?";
		PreparedStatementSetter pss = ps -> ps.setString(1, login);
		List<Customer> customers = jdbcTemplate.query(sql, pss, customerMapper);
		return customers.get(0);
	}
}
