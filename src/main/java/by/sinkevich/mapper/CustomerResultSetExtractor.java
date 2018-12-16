package by.sinkevich.mapper;

import by.sinkevich.model.Account;
import by.sinkevich.model.CreditCard;
import by.sinkevich.model.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerResultSetExtractor implements ResultSetExtractor<List<Customer>> {

	@Override
	public List<Customer> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long, Customer> customers = new HashMap<>();

		while (rs.next()) {
			long customerId = rs.getLong("customer_id");
			Customer customer;
			if (customers.containsKey(customerId)) {
				customer = customers.get(customerId);
			} else {
				customer = new Customer();
				customer.setId(rs.getLong("customer_id"));
				customer.setName(rs.getString("name"));
				customer.setLogin(rs.getString("login"));
				customer.setPassword(rs.getString("password"));
			}

			CreditCard creditCard = new CreditCard();
			creditCard.setId(rs.getLong("credit_card_id"));
			creditCard.setNumber(rs.getInt("number"));

			Account account = new Account();
			account.setId(rs.getLong("account_id"));
			account.setBalance(rs.getDouble("balance"));
			account.setActive(rs.getBoolean("active"));

			creditCard.setCustomer(customer);
			creditCard.setAccount(account);
			account.setCreditCard(creditCard);

			if (customers.containsKey(customerId)) {
				customer.getCreditCards().add(creditCard);
			} else {
				List<CreditCard> creditCards = new ArrayList<>();
				creditCards.add(creditCard);
				customer.setCreditCards(creditCards);
			}
			customers.put(customerId, customer);
		}
		return new ArrayList<>(customers.values());
	}
}
