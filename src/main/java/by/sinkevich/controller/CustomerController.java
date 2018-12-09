package by.sinkevich.controller;

import by.sinkevich.model.Customer;
import by.sinkevich.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
@SessionAttributes("customer")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("")
	public String customerPage(@SessionAttribute(required = false) Customer customer) {
		if (customer == null) {
			return "redirect:/login";
		} else {
			return "redirect:" + customer.getId();
		}
	}

	@GetMapping("/{id}")
	public String getCustomerById() {
		return "customer/customer";
	}

	/*@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		model.addAttribute("customers", customerService.findAll());
		return "customers";
	}
*/
/*
	@GetMapping("/delete/{id}")
	public String deleteCustomerById(@PathVariable(value = "id") long id) {
		customerService.delete(id);
		return "redirect:/customers";
	}
*/

	@PostMapping("/register")
	public String registerCustomer(@ModelAttribute("customerData") Customer customerData, Model model) {
		customerService.save(customerData);
		model.addAttribute("customer", customerData);
		return "redirect:" + customerData.getId();
	}

	@PostMapping("/login")
	public String loginCustomer(@ModelAttribute("customerData") Customer customerData, Model model) {
		Customer customer = customerService.login(customerData.getLogin(), customerData.getPassword());
		model.addAttribute("customer", customer);
		return "redirect:" + customer.getId();
	}
	/*@GetMapping("/update/{id}")
	public String updateCustomerForm(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("customer", customerService.readById(id));
		return "updateCustomer";
	}
*//*
	@PostMapping("/update/updateUser")
	public String updateCustomer(@ModelAttribute Customer customer) {
		customerService.update(customer);
		return "redirect:/customers";
	}*/
}
