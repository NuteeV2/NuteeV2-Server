package kr.nutee.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * 카테고리 추기 시 사용할 Dto
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class CategoryInsertRequestDto {

	@NotBlank(message="CategoryName requires not blank value")
	private String categoryName;

	@NotNull(message="BoardId requires not null")
	private int boardId;

}
