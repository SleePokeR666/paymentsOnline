package by.sinkevich.controller;

import by.sinkevich.model.Account;
import by.sinkevich.model.CreditCard;
import by.sinkevich.model.Customer;
import by.sinkevich.model.Payment;
import by.sinkevich.service.AccountService;
import by.sinkevich.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller("/")
public class AccountController {

	private AccountService accountService;

	private PaymentService paymentService;

	public AccountController() {

	}

	@Autowired
	public AccountController(AccountService accountService, PaymentService paymentService) {
		this.accountService = accountService;
		this.paymentService = paymentService;
	}

	@GetMapping("customer/account/{accountId}/block")
	public String block(@SessionAttribute Customer customer, @PathVariable long accountId) {
		Account account = getAccountByIdFromCustomer(customer, accountId);
		if (account != null) {
			account.setIsActive(false);
		}
		accountService.update(account);
		return "redirect:/customer/" + customer.getId();
	}

	@GetMapping("customer/account/{accountId}/deposit")
	public String go2depositPage() {
		return "customer/deposit";
	}

	@PostMapping("customer/account/{accountId}/deposit")
	public String deposit(@SessionAttribute Customer customer, @PathVariable long accountId, @ModelAttribute Payment payment) {
		Account account = getAccountByIdFromCustomer(customer, accountId);
		payment.setDate(new Date());
		payment.setToAccount(account);
		payment.setStatus("completed");
		paymentService.save(payment);
		account.setBalance(account.getBalance() + payment.getAmount());
		accountService.update(account);
		return "redirect:/customer/" + customer.getId();
	}

	private Account getAccountByIdFromCustomer(Customer customer, long accountId) {
		return customer.getCreditCards().stream()
				.filter(creditCard -> creditCard.getAccount().getId() == accountId)
				.map(CreditCard::getAccount)
				.findFirst()
				.orElse(null);
	}
}
