package kr.nutee.dao;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
 * User 테이블의 데이터를 담기 위한 DTO 클래스
 *
 * @author choiyk
 */
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

	private int id;
	private String nickname;
	private String pw;
	private String studentNumber;
	private int roleId;
	private int coution;
	private String email;

}
