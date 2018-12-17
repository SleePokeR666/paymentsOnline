package by.sinkevich.model;

import java.util.List;

public class Account {

	private long id;
	private Double balance;
	private boolean isActive;
	private CreditCard creditCard;
	private List<Payment> paymentsHistory;

	public Account() {
	}

	public Account(long id, Double balance, boolean isActive, CreditCard creditCard, List<Payment> paymentsHistory) {
		this.id = id;
		this.balance = balance;
		this.isActive = isActive;
		this.creditCard = creditCard;
		this.paymentsHistory = paymentsHistory;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean active) {
		isActive = active;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public List<Payment> getPaymentsHistory() {
		return paymentsHistory;
	}

	public void setPaymentsHistory(List<Payment> paymentsHistory) {
		this.paymentsHistory = paymentsHistory;
	}

	@Override
	public String toString() {
		return "Account{" +
				"id=" + id +
				", balance=" + balance +
				'}';
	}
}
