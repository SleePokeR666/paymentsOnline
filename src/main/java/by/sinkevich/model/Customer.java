package by.sinkevich.model;

import java.util.List;
import java.util.Objects;

public class Customer {

	private long id;
	private String name;
	private String login;
	private String password;
	private List<CreditCard> creditCards;
	private boolean isAdmin;

	public Customer() {

	}

	public Customer(long id, String name, String login, String password,
					List<CreditCard> creditCards, boolean isAdmin) {
		this.id = id;
		this.name = name;
		this.login = login;
		this.password = password;
		this.creditCards = creditCards;
		this.isAdmin = isAdmin;
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

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean admin) {
		this.isAdmin = admin;
	}

	public String toString() {
		return "Customer{" +
				"id=" + id +
				", name='" + name + '\'' +
				", login='" + login + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Customer)) return false;
		Customer customer = (Customer) o;
		return id == customer.id &&
				name.equals(customer.name) &&
				login.equals(customer.login) &&
				password.equals(customer.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, login, password);
	}
}
