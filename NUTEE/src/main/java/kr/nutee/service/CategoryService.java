package kr.nutee.service;

import java.util.List;

import kr.nutee.dao.Category;
import kr.nutee.model.Category.CategoryInsertRequestDto;
import kr.nutee.model.Category.CategoryListResponseDto;

/*
 * Category Service Interface
 *
 * @author choiyk
 */
public interface CategoryService {

	//Board id에 해당하는 Category List 출력
	List<CategoryListResponseDto> findAllByBoardId(int boardId);

	//Category 하나 검색
	Category findOne(int id);

	//게시판에 카테고리 추가
	void insert(CategoryInsertRequestDto category);

	//카테고리 이름 변경
	void update(int id, String category);

	//카테고리 삭제
	void delete(int id);

}
