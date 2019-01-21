package kr.nutee.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Data가 유효하지 않은 경우 발생하는 Exception
 * @author choiyk
 */
public class InvalidDataException extends Exception{

	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public InvalidDataException(String message) {
		super(message);
		logger.info("InvalidDataException: {}", message);
	}

}
