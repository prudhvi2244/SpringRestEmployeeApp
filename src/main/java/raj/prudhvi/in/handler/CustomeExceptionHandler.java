package raj.prudhvi.in.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import raj.prudhvi.in.exception.EmployeeNotFoundException;

@RestControllerAdvice
public class CustomeExceptionHandler {

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException enfe) {
		return new ResponseEntity<String>(enfe.getMessage(), HttpStatus.NOT_FOUND);
	}

}
