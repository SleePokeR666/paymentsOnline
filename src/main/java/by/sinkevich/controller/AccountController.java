package by.sinkevich.controller;

import by.sinkevich.model.Account;
import by.sinkevich.model.CreditCard;
import by.sinkevich.model.Customer;
import by.sinkevich.model.Payment;
import by.sinkevich.service.AccountService;
import by.sinkevich.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
		String view = "redirect:/customer/" + customer.getId();
		if (account == null) {
			account = accountService.readById(accountId);
			view = "redirect:/customer/customers";
		}
		account.setIsActive(false);
		accountService.update(account);
		return view;
	}

	@GetMapping("customer/account/{accountId}/unblock")
	public String unBlock(@PathVariable long accountId) {
		Account account = accountService.readById(accountId);
		account.setIsActive(true);
		accountService.update(account);
		return "redirect:/customer/customers";
	}

	@GetMapping("customer/account/{accountId}/deposit")
	public String go2DepositPage() {
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

	@GetMapping("customer/account/{accountId}/transfer")
	public String go2TransferPage() {
		return "customer/transfer";
	}

	@PostMapping("customer/account/{accountId}/transfer")
	public String transfer(
			@SessionAttribute Customer customer,
			@PathVariable long accountId,
			@ModelAttribute Payment payment,
			@RequestParam long targetAccountId) {
		Account fromAccount = getAccountByIdFromCustomer(customer, accountId);
		Account toAccount = accountService.readById(targetAccountId);
		payment.setDate(new Date());
		payment.setStatus("completed");
		payment.setFromAccount(fromAccount);
		payment.setToAccount(toAccount);
		paymentService.save(payment);

		fromAccount.setBalance(fromAccount.getBalance() - payment.getAmount());
		toAccount.setBalance(toAccount.getBalance() + payment.getAmount());
		accountService.update(fromAccount);
		accountService.update(toAccount);
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
