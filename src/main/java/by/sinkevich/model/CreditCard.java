package by.sinkevich.model;

public class CreditCard {

	private long id;
	private int number;
	private Customer customer;
	private Account account;

	public CreditCard() {
	}

	public CreditCard(long id, int number, Customer customer, Account account) {
		this.id = id;
		this.number = number;
		this.customer = customer;
		this.account = account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
