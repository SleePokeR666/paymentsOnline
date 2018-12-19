package by.sinkevich.dao.impl;

import by.sinkevich.dao.AccountDao;
import by.sinkevich.mapper.AccountRowMapper;
import by.sinkevich.model.Account;
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
public class AccountDaoImpl implements AccountDao {

	private static final String INSERT_INTO_ACCOUNT_SQL = "INSERT INTO account (balance, active) VALUES (?, ?)";
	private static final String READ_ACCOUNT_BY_ID_SQL = "SELECT * FROM account WHERE id = ?";
	private static final String UPDATE_ACCOUNT_SQL = "UPDATE account SET balance = ?, active = ? WHERE id = ?";

	private JdbcTemplate jdbcTemplate;

	private AccountRowMapper rowMapper;

	@Autowired
	public AccountDaoImpl(JdbcTemplate jdbcTemplate, AccountRowMapper rowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.rowMapper = rowMapper;
	}

	@Override
	public long save(Account account) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = con -> {
			PreparedStatement ps = con.prepareStatement(INSERT_INTO_ACCOUNT_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, account.getBalance());
			ps.setBoolean(2, account.getIsActive());
			return ps;
		};
		jdbcTemplate.update(psc, keyHolder);
		Number key = keyHolder.getKey();
		return key != null ? key.longValue() : 0;
	}

	@Override
	public Account readById(long id) {
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		List<Account> accounts = jdbcTemplate.query(READ_ACCOUNT_BY_ID_SQL, pss, rowMapper);
		return accounts.size() != 0 ? accounts.get(0) : null;
	}

	@Override
	public void update(Account account) {
		PreparedStatementSetter pss = ps -> {
			ps.setDouble(1, account.getBalance());
			ps.setBoolean(2, account.getIsActive());
			ps.setLong(3, account.getId());
		};
		jdbcTemplate.update(UPDATE_ACCOUNT_SQL, pss);
	}
}
