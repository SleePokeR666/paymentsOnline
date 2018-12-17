package by.sinkevich.controller;

import by.sinkevich.model.Account;
import by.sinkevich.model.CreditCard;
import by.sinkevich.model.Customer;
import by.sinkevich.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;

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
		Account account = customer.getCreditCards().stream()
				.filter(creditCard -> creditCard.getAccount().getId() == accountId)
				.map(CreditCard::getAccount)
				.findFirst()
				.orElse(null);
		if (account != null) {
			account.setIsActive(false);
		}
		accountService.update(account);
		return "redirect:/customer/" + customer.getId();
	}
}
