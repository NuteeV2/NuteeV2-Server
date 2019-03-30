package kr.nutee.dao;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * User 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

	private int id;
	//private long id;

	private int coution;

	@NotBlank(message="nickname requires non blank value")
	private String nickname;

	@NotBlank(message="pw requires non blank value")
	private String pw;

	@NotBlank(message="studentNumber requires non blank value")
	private String studentNumber;

	private int roleId;

	@NotBlank(message="email requires non blank value")
	private String email;

}
