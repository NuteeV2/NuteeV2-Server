package kr.nutee.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.model.ResponseBody;

/*
 * Error 발생 시 ResponseEntity 형태로 반환하기 위한 Handler
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
	public ResponseEntity<ResponseBody> handleBadRequestException(BadRequestException e) {
		logger.info("BadRequestException: {}", e.getMessage());
		ResponseBody message = new ResponseBody(new Date(), HttpStatus.BAD_REQUEST, e.getMessage());
		return new ResponseEntity<ResponseBody>(message, HttpStatus.BAD_REQUEST);
	}

}
