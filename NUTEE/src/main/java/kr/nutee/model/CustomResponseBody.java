package kr.nutee.model;

import java.util.List;

import lombok.Getter;
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
@ToString
public class CustomResponseBody {

	private Object data;
	private List<Error> errors;

	/*
	 * 응답 성공 시 사용, return할 객체가 없는 경우
	 */
	public CustomResponseBody() {
		this.data = null;
		this.errors = null;
	}

	/*
	 * 응답 성공 시 사용
	 * @param return 할 data
	 */
	public CustomResponseBody(Object data) {
		this.data = data;
		this.errors = null;
	}

	/*
	 * 응답 에러 발생 시 사용
	 * @param 에러 메세지 list
	 */
	public CustomResponseBody(List<Error> errors){
		this.data = null;
		this.errors = errors;
	}

}
