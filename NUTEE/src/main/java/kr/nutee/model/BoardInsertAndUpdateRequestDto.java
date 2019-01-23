package kr.nutee.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/*
 * Board 추가 시 사용할 Dto
 * @author choiyk
 */
@Data
public class BoardInsertAndUpdateRequestDto {

	@NotBlank(message="BoardName requires non blank value")
	private String boardName;

}
