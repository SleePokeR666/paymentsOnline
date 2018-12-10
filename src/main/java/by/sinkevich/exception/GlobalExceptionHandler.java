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
		String errorMessage = exception.getMessage();
		log.error(errorMessage, exception);
		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}
}
