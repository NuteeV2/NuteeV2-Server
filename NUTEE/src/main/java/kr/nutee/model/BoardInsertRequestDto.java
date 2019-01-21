package kr.nutee.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/*
 * Board 추가 시 사용할 Dto
 * @author choiyk
 */
@Data
public class BoardInsertRequestDto {

	@NotNull(message="BoardName requires valid value")
	@NotEmpty(message="BoardName requires non empty value")
	private String boardName;

}
