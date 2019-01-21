package kr.nutee.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * 잘못된 url 요청이 있을 때 발생
 *
 * @author choiyk
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "BadRequest")
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public BadRequestException(String message) {
		super(message);
		logger.info("BadRequestException: {}", message);
	}

}
