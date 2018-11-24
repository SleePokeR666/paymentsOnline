package by.sinkevich.controller;

import by.sinkevich.model.Customer;
import by.sinkevich.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class CustomerController {

	private final CustomerService customerService;

	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping({"/", "/index"})
	public String index() {
		return "index";
	}

	@GetMapping("/customer/{id}")
	public String getCustomerById(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("customer", customerService.readById(id));
		return "customer";
	}

	@GetMapping("/customers")
	public String getAllCustomers(Model model) {
		model.addAttribute("customers", customerService.findAll());
		return "customers";
	}

	@GetMapping("/delete/{id}")
	public String deleteCustomerById(@PathVariable(value = "id") long id) {
		customerService.delete(id);
		return "redirect:/customers";
	}

	@GetMapping("/register")
	public String go2RegisterForm() {
		return "register";
	}

	@PostMapping("/register")
	public String registerCustomer(@ModelAttribute Customer customer) {
		customerService.save(customer);
		return "redirect:/customers";
	}

	@GetMapping("/update/{id}")
	public String updateCustomerForm(@PathVariable(value = "id") long id, Model model) {
		model.addAttribute("customer", customerService.readById(id));
		return "updateCustomer";
	}

	@PostMapping("/update/updateUser")
	public String updateCustomer(@ModelAttribute Customer customer) {
		customerService.update(customer);
		return "redirect:/customers";
	}
}
