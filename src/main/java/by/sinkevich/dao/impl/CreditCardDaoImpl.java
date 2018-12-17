package by.sinkevich.dao.impl;

import by.sinkevich.dao.CreditCardDao;
import by.sinkevich.mapper.CreditCardRowMapper;
import by.sinkevich.model.CreditCard;
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
public class CreditCardDaoImpl implements CreditCardDao {

	private JdbcTemplate jdbcTemplate;

	private CreditCardRowMapper rowMapper;

	@Autowired
	public CreditCardDaoImpl(JdbcTemplate jdbcTemplate, CreditCardRowMapper rowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.rowMapper = rowMapper;
	}

	@Override
	public long save(CreditCard creditCard) {
		String sql = "INSERT INTO credit_card (number, customer_id, account_id) VALUES (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = con -> {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, creditCard.getNumber());
			ps.setLong(2, creditCard.getCustomer().getId());
			ps.setLong(3, creditCard.getAccount().getId());
			return ps;
		};
		jdbcTemplate.update(psc, keyHolder);
		Number key = keyHolder.getKey();
		return key != null ? key.longValue() : 0;
	}

	@Override
	public CreditCard readById(long id) {
		String sql = "SELECT * FROM credit_card WHERE id = ?";
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		List<CreditCard> creditCards = jdbcTemplate.query(sql, pss, rowMapper);
		return creditCards.get(0);
	}

	@Override
	public void update(CreditCard creditCard) {
		String sql = "UPDATE credit_card SET number = ? WHERE id = ?";
		PreparedStatementSetter pss = ps -> {
			ps.setInt(1, creditCard.getNumber());
			ps.setLong(2, creditCard.getId());
		};
		jdbcTemplate.update(sql, pss);
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM credit_card WHERE id = ?";
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		jdbcTemplate.update(sql, pss);
	}

	@Override
	public List<CreditCard> findAll() {
		String sql = "SELECT * FROM credit_card";
		return jdbcTemplate.query(sql, rowMapper);
	}
}
