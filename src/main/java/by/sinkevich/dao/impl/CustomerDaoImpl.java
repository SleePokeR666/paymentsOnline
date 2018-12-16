package by.sinkevich.dao.impl;

import by.sinkevich.dao.CustomerDao;
import by.sinkevich.mapper.CustomerMapper;
import by.sinkevich.mapper.CustomerResultSetExtractor;
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

	private static final String SAVE_CUSTOMER_SQL = "INSERT INTO customer (name, login, password) VALUES (?, ?, ?)";
	private static final String READ_CUSTOMER_BY_ID_LAZY_SQl = "SELECT * FROM customer WHERE id = ?";
	private static final String READ_CUSTOMER_BY_ID_SQl =
			"SELECT customer.id AS customer_id, customer.name, customer.login, customer.password, customer.is_admin, " +
					"credit_card.id AS credit_card_id, credit_card.number, " +
					"account.id AS account_id, account.balance, account.active " +
					"FROM customer, credit_card, account " +
					"WHERE customer.id = credit_card.customer_id AND credit_card.account_id = account.id " +
					"AND customer.id = ?";
	private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET name = ?, login = ?, password = ?, is_admin = ? WHERE id = ?";
	private static final String DELETE_CUSTOMER_BY_ID_SQL = "DELETE FROM customer WHERE id = ?";
	private static final String FIND_ALL_CUSTOMERS_SQL_LAZY = "SELECT * FROM customer";
	private static final String FIND_ALL_CUSTOMERS_SQL_NOT_LAZY =
			"SELECT customer.id AS customer_id, customer.name, customer.login, customer.password, customer.is_admin, " +
			"credit_card.id AS credit_card_id, credit_card.number, " +
			"account.id AS account_id, account.balance, account.active " +
			"FROM customer, credit_card, account " +
			"WHERE customer.id = credit_card.customer_id AND credit_card.account_id = account.id";
	private static final String READ_CUSTOMER_BY_LOGIN_SQL = "SELECT * FROM customer WHERE login = ?";

	private JdbcTemplate jdbcTemplate;

	private CustomerMapper customerMapper;

	@Autowired
	public CustomerDaoImpl(JdbcTemplate jdbcTemplate, CustomerMapper customerMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.customerMapper = customerMapper;
	}

	@Override
	public long save(Customer customer) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = con -> {
			PreparedStatement ps = con.prepareStatement(SAVE_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS);
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
	public Customer readByIdLazy(long id) {
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		List<Customer> customers = jdbcTemplate.query(READ_CUSTOMER_BY_ID_LAZY_SQl, pss, customerMapper);
		return customers.get(0);
	}

	@Override
	public Customer readById(long id) {
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		List<Customer> customers = jdbcTemplate.query(READ_CUSTOMER_BY_ID_SQl, pss, new CustomerResultSetExtractor());
		return customers.get(0);
	}

	@Override
	public void update(Customer customer) {
		PreparedStatementSetter pss = ps -> {
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getLogin());
			ps.setString(3, customer.getPassword());
			ps.setBoolean(4, customer.getIsAdmin());
			ps.setLong(5, customer.getId());
		};
		jdbcTemplate.update(UPDATE_CUSTOMER_SQL, pss);
	}

	@Override
	public void delete(long id) {
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		jdbcTemplate.update(DELETE_CUSTOMER_BY_ID_SQL, pss);
	}

	@Override
	public List<Customer> findAll() {
		return jdbcTemplate.query(FIND_ALL_CUSTOMERS_SQL_LAZY, customerMapper);
	}

	@Override
	public Customer readByLogin(String login) {
		PreparedStatementSetter pss = ps -> ps.setString(1, login);
		List<Customer> customers = jdbcTemplate.query(READ_CUSTOMER_BY_LOGIN_SQL, pss, customerMapper);
		return customers.get(0);
	}
}
