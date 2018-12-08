package by.sinkevich.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

	@GetMapping({"/", "/index"})
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String go2RegisterPage() {
		return "register";
	}

	@GetMapping("/login")
	public String go2LoginPage() {
		return "login";
	}
}
