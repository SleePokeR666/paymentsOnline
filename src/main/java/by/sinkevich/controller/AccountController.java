package by.sinkevich.controller;

import by.sinkevich.model.Account;
import by.sinkevich.model.CreditCard;
import by.sinkevich.model.Customer;
import by.sinkevich.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("/")
public class AccountController {

	private AccountService accountService;

	public AccountController() {

	}

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("customer/account/{accountId}/block")
	public String block(@SessionAttribute Customer customer, @PathVariable long accountId) {
		Account account = getAccountByIdFromCustomer(customer, accountId);
		String view;
		if (account != null) {
			accountService.block(account);
			view = "redirect:/customer/" + customer.getId();
		} else {
			accountService.blockById(accountId);
			view = "redirect:/customer/customers";
		}
		return view;
	}

	@GetMapping("customer/account/{accountId}/unblock")
	public String unBlock(@PathVariable long accountId) {
		accountService.unBlock(accountId);
		return "redirect:/customer/customers";
	}

	@GetMapping("customer/account/{accountId}/deposit")
	public String go2DepositPage() {
		return "customer/deposit";
	}

	@PostMapping("customer/account/{accountId}/deposit")
	public String deposit(@SessionAttribute Customer customer,
						  @PathVariable long accountId,
						  @RequestParam Double amount) {
		Account account = getAccountByIdFromCustomer(customer, accountId);
		accountService.deposit(account, amount);
		return "redirect:/customer/" + customer.getId();
	}

	@GetMapping("customer/account/{accountId}/transfer")
	public String go2TransferPage(@SessionAttribute Customer customer,
								  @PathVariable long accountId,
								  Model model) {
		Account account = getAccountByIdFromCustomer(customer, accountId);
		model.addAttribute("balance", account.getBalance());
		return "customer/transfer";
	}

	@PostMapping("customer/account/{accountId}/transfer")
	public String transfer(
			@SessionAttribute Customer customer,
			@PathVariable long accountId,
			@RequestParam Double amount,
			@RequestParam long targetAccountId) {
		Account fromAccount = getAccountByIdFromCustomer(customer, accountId);
		Account toAccount = accountService.readById(targetAccountId);
		accountService.transfer(fromAccount, toAccount, amount);
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
