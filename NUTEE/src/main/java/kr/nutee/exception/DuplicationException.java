package kr.nutee.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

/*
 * 데이터 중복 시 발생하는 error
 * @author choiyk
 */
@Getter
public class DuplicationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	//Logging
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private String field;

	public DuplicationException(String field, String msg) {
		super(msg);
		this.field = field;
	}

}
