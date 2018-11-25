package by.sinkevich.mapper;

import by.sinkevich.model.CreditCard;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CreditCardMapper implements RowMapper<CreditCard> {

	@Override
	public CreditCard mapRow(ResultSet rs, int rowNum) throws SQLException {
		CreditCard creditCard = new CreditCard();
		creditCard.setId(rs.getLong("id"));
		creditCard.setNumber(rs.getInt("number"));
		return creditCard;
	}
}
