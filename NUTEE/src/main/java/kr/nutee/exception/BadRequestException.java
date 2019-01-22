package kr.nutee.exception;

/*
 * 잘못된 url 요청이 있을 때 발생
 *
 * @author choiyk
 */
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

}
