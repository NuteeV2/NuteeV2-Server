package kr.nutee.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * 응답 시 보낼 객체
 * 200 응답 시 ResponseBody(Date timestamp, Object data) 형태로 사용
 * 응답 에러 시 ResponseBody(Date timestamp, HttpStatus status, String errorMessage) 형태로 사용
 * @author choiyk
 */
@Getter
@ToString
public class CustomResponseBody {

	private Date timestamp;
	private int status;
	private Error error;
	private Object data;

	@Getter
	@AllArgsConstructor
	@ToString
	private static class Error{
		private String error;
		private String message;
	}

	/*
	 * 응답 성공 시 사용, return할 객체가 없는 경우
	 * @param status code
	 */
	public CustomResponseBody(HttpStatus status) {
		this.timestamp = new Date();
		this.status = status.value();
		this.error = null;
		this.data = null;
	}

	/*
	 * 응답 성공 시 사용
	 * @param status code, return 할 data
	 */
	public CustomResponseBody(HttpStatus status, Object data) {
		this.timestamp = new Date();
		this.status = status.value();
		this.error = null;
		this.data = data;
	}

	/*
	 * 응답 에러 발생 시 사용
	 * @param 상태 코드, 에러 메세지
	 */
	public CustomResponseBody(HttpStatus status, String errorMessage){
		this.timestamp = new Date();
		this.status = status.value();
		this.error = new Error(status.getReasonPhrase(), errorMessage);
		this.data = null;
	}

}
