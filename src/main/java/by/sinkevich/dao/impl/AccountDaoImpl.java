package by.sinkevich.dao.impl;

import by.sinkevich.dao.AccountDao;
import by.sinkevich.mapper.AccountMapper;
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

	private JdbcTemplate jdbcTemplate;

	private AccountMapper accountMapper;

	@Autowired
	public AccountDaoImpl(JdbcTemplate jdbcTemplate, AccountMapper accountMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.accountMapper = accountMapper;
	}

	@Override
	public long save(Account account) {
		String sql = "INSERT INTO account (balance, active) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = con -> {
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setDouble(1, account.getBalance());
			ps.setBoolean(2, account.isActive());
			return ps;
		};
		jdbcTemplate.update(psc, keyHolder);
		Number key = keyHolder.getKey();
		return key != null ? key.longValue() : 0;
	}

	@Override
	public Account readById(long id) {
		String sql = "SELECT * FROM account WHERE id = ?";
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		List<Account> accounts = jdbcTemplate.query(sql, pss, accountMapper);
		return accounts.get(0);
	}

	@Override
	public void update(Account account) {
		String sql = "UPDATE account SET balance = ?, active = ? WHERE id = ?";
		PreparedStatementSetter pss = ps -> {
			ps.setDouble(1, account.getBalance());
			ps.setBoolean(2, account.isActive());
			ps.setLong(3, account.getId());
		};
		jdbcTemplate.update(sql, pss);
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM account WHERE id = ?";
		PreparedStatementSetter pss = ps -> ps.setLong(1, id);
		jdbcTemplate.update(sql, pss);
	}

	@Override
	public List<Account> findAll() {
		String sql = "SELECT * FROM account";
		return jdbcTemplate.query(sql, accountMapper);
	}
}
