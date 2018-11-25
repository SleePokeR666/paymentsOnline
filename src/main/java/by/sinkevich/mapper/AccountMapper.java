package by.sinkevich.mapper;

import by.sinkevich.model.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setId(rs.getLong("id"));
		account.setBalance(rs.getDouble("balance"));
		account.setActive(rs.getBoolean("active"));
		return account;
	}
}
