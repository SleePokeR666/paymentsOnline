package by.sinkevich.dao.impl;

import by.sinkevich.dao.CreditCardDao;
import by.sinkevich.model.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class CreditCardDaoImpl implements CreditCardDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public CreditCardDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
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
}
