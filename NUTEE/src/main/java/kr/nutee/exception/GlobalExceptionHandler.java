package kr.nutee.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.model.CustomError;
import kr.nutee.model.CustomResponseBody;

/*
 * Error 발생 시 ResponseEntity 형태로 반환하기 위한 ExceptionHandler
 * @author choiyk
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	//Logging
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Validation error시 발생하는 Exception을 위한 Handler
	 * @param MethodArgumentNotValidException
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<CustomResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		List<CustomError> errors = new ArrayList<>();
		for(FieldError oe : e.getBindingResult().getFieldErrors()) {
			CustomError error = new CustomError(oe.getField(), oe.getDefaultMessage());
			errors.add(error);
		}
		logger.debug("MethodArgumentNotValidException: {}", errors.toString());
		CustomResponseBody body = new CustomResponseBody(null, errors);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.BAD_REQUEST);
	}

	/*
	 * 데이터 중복 시 발생하는 Exception을 위한 Handler
	 * @param OverlappedException
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@ExceptionHandler(value = DuplicationException.class)
	public ResponseEntity<CustomResponseBody> handleOverlappedException(DuplicationException e){
		List<CustomError> errors = new ArrayList<>();
		CustomError error = new CustomError(e.getField(), e.getMessage());
		errors.add(error);
		logger.debug("OverlappedException: {}", errors.toString());
		CustomResponseBody body = new CustomResponseBody(null, errors);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.CONFLICT);
	}

}
