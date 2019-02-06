package kr.nutee.model;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/*
 * 카테고리 수정 시 사용할 Dto
 * @author choiyk
 */
@Getter
@AllArgsConstructor
@ToString
public class CategoryUpdateRequestDto {

	@NotBlank(message="Category name requires not blank value")
	private String categoryName;

}
