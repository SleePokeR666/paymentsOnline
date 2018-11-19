package by.sinkevich.model;

import java.util.List;

public class Customer {

	private long id;
	private String name;
	private String login;
	private String password;
	private List<CreditCard> creditCards;

	public Customer() {
	}

	public Customer(long id, String name, String login, String password, List<CreditCard> creditCards) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.creditCards = creditCards;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}
}
