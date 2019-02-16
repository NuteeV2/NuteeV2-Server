package kr.nutee.model.user;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User model for update
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserUpdateModel {
	
	private int id;
	
	@NotBlank(message="nickname requires non blank value")
	private String nickname;
	
	@NotBlank(message="pw requires non blank value")
	private String pw;
	
	@NotBlank(message="email requires non blank value")
	private String email;
	
}
