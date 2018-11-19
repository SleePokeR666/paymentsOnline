package by.sinkevich.model;

import java.sql.Date;

public class Payment {

	private long id;
	private Double amount;
	private Date date;
	private String status;
	private Account fromAccount;
	private Account toAccount;

	public Payment() {
	}

	public Payment(long id, Double amount, Date date, String status, Account fromAccount, Account toAccount) {
		this.id = id;
		this.amount = amount;
		this.date = date;
		this.status = status;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Account getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
}
