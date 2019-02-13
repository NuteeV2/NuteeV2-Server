package kr.nutee.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

/*
 * 카테고리 추기 시 사용할 Dto
 * @author choiyk
 */
@Data
public class CategoryInsertRequestDto {

	@NotBlank(message="CategoryName requires not blank value")
	private String categoryName;

	@NotNull(message="BoardId cannot be null")
	private int boardId;

}
