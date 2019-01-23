package kr.nutee.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * Board 추가 시 사용할 Dto
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class BoardInsertAndUpdateRequestDto {

	@NotBlank(message="BoardName requires non blank value")
	private String boardName;

}
