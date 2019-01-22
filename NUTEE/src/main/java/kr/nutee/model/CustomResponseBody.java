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
public class ResponseBody {

	private Date timestamp;
	private HttpStatus status;
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
	 * 응답 성공 시 사용
	 * @param 현재 시간, return 할 data
	 */
	public ResponseBody(Date timestamp, Object data) {
		this.timestamp = timestamp;
		this.status = HttpStatus.OK;
		this.error = null;
		this.data = data;
	}

	/*
	 * 응답 에러 발생 시 사용
	 * @param 현재 시간, 상태 코드, 에러 메세지
	 */
	public ResponseBody(Date timestamp, HttpStatus status, String errorMessage){
		this.timestamp = timestamp;
		this.status = status;
		this.error = new Error(status.getReasonPhrase(), errorMessage);
		this.data = null;
	}

}
