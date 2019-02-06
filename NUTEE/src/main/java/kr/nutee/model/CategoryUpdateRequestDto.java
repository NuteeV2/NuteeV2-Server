package kr.nutee.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/*
 * 카테고리 수정 시 사용할 Dto
 * @author choiyk
 */
@Data	//update시 ibatis에서 객체 생성할 때 Setter를 쓰는 듯 함. Setter가 없으면 error 빌생.
public class CategoryUpdateRequestDto {

	@NotBlank(message="Category name requires not blank value")
	private String categoryName;

}
