package kr.nutee.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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
	 * 잘못된 url 요청이 있을 때 발생하는 Exception인 BadREquestException Handler
	 * @param BadRequestException
	 * @return ResponseEntity<ResponseBody>
	 */
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<CustomResponseBody> handleBadRequestException(BadRequestException e) {
		logger.info("BadRequestException: {}", e.getMessage());
		CustomResponseBody body = new CustomResponseBody(HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.BAD_REQUEST);
	}

	/*
	 * parameter 값이 invalid한 경우 발생하는 Exception을 위한 Handler
	 * @param MethodArgumentNotValidException
	 * @return ResponseEntity<ResponseBody>
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<CustomResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
		String errorMessage = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
		logger.info("MethodArgumentNotValidException: {}", errorMessage);
		CustomResponseBody body = new CustomResponseBody(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/*
	 * DB insert나 update 시 키 제약에 위반할 경우 발생하는 Exception을 위한 Handler
	 * @param DataIntegrityViolationException
	 * @return ResponseEntity<ResponseBody>
	 */
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<CustomResponseBody> handleDataIntegrityViolationException(DataIntegrityViolationException e){
		String errorMessage = e.getMessage();
		logger.info("DataIntegrityViolationException: {}", errorMessage);
		CustomResponseBody body = new CustomResponseBody(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage);
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
