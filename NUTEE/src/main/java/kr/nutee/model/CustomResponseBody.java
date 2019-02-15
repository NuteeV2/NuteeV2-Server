package kr.nutee.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * 응답 시 보낼 객체
 * 200 응답 시 return할 객체가 없으면 CustomResponseBody() 형태로 사용
 * return할 객체가 있으면 CustomResponseBody(Object data) 형태로 사용
 * 응답 에러 시 CustomResponseBody(int errorCode, List<Error> errors) 형태로 사용
 *
 * @author choiyk
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CustomResponseBody {

	private Object data;
	private List<CustomError> errors;

	public CustomResponseBody() {
		this.data = null;
		this.errors = null;
	}

}
