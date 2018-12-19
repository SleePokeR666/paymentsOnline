package by.sinkevich.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LogManager.getLogger();

	@ExceptionHandler
	public String handleException(Exception exception, Model model) {
		log.error(exception.getMessage(), exception);
		model.addAttribute("errorMessage", "Произошла непоправимая ошибка! " +
				"Попробуйте зайти ещё раз. Просим прощения за причинённые неудобства!");
		return "error";
	}

	@ExceptionHandler(RegistrationException.class)
	public String handleRegistrationException(RegistrationException exception, Model model) {
		model.addAttribute("errorMessage", exception.getMessage());
		return "register";
	}
}
