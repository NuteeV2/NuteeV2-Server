package kr.nutee.model.Category;

import lombok.Data;

/*
 * Category 전체 List 조회시 데이터를 담을 Dto
 * @author choiyk
 */
@Data
public class CategoryListResponseDto {

	private int id;
	private String categoryName;

}
