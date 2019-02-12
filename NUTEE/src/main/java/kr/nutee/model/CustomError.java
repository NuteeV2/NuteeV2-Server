package kr.nutee.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * 응답 error를 담을 객체
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class CustomError {

	private String field;
	private String detail;

}
