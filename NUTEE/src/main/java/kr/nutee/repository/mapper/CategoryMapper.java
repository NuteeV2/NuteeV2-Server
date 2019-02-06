package kr.nutee.repository.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.nutee.dto.Category;
import kr.nutee.model.CategoryInsertRequestDto;
import kr.nutee.model.CategoryUpdateRequestDto;

/*
 * Category Mapper Interface
 * @author choiyk
 */
@Mapper
public interface CategoryMapper {

	//Board id에 해당하는 Category List 출력
	List<Category> findAllByBoardId(int boardId);

	//게시판에 카테고리 추가
	void insert(CategoryInsertRequestDto category);

	//카테고리 이름 변경
	void update(@Param("id") int id, @Param("category") CategoryUpdateRequestDto category);

	//카테고리 삭제
	void delete(int id);

}
