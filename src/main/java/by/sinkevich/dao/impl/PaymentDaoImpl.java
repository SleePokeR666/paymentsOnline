package by.sinkevich.dao.impl;

import by.sinkevich.dao.PaymentDao;
import by.sinkevich.model.Account;
import by.sinkevich.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PaymentDaoImpl implements PaymentDao {

	private static final String SAVE_PAYMENT_SQL = "INSERT INTO payment " +
			"(amount, date, status, account_id_from, account_id_to) VALUES (?, ?, ?, ?, ?)";

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public PaymentDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long save(Payment payment) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = con -> {
			PreparedStatement ps = con.prepareStatement(SAVE_PAYMENT_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, payment.getAmount());
			ps.setDate(2, new Date(payment.getDate().getTime()));
			ps.setString(3, payment.getStatus());

			Account account = payment.getFromAccount();
			if (account != null) {
				ps.setLong(4, account.getId());
			} else {
				ps.setLong(4, 0L);
			}

			account = payment.getToAccount();
			if (account != null) {
				ps.setLong(5, account.getId());
			} else {
				ps.setLong(5, 0L);
			}
			return ps;
		};
		jdbcTemplate.update(psc, keyHolder);
		Number key = keyHolder.getKey();
		return key != null ? key.longValue() : 0;
	}
}
