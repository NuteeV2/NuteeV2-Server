package kr.nutee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * 조회 결과가 null일 경우 발생시킬 예외
 *
 * @author choiyk
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "NonExist")
public class NonExistException extends RuntimeException{

	public NonExistException(String message) {
		super(message);
	}

}
