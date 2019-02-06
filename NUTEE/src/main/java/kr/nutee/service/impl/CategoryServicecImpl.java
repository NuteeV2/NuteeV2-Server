package kr.nutee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dto.Category;
import kr.nutee.model.CategoryInsertRequestDto;
import kr.nutee.model.CategoryUpdateRequestDto;
import kr.nutee.repository.mapper.CategoryMapper;
import kr.nutee.service.CategoryService;

/*
 * Category Service 구현 클래스
 * @author choiyk
 */
@Service
public class CategoryServicecImpl implements CategoryService{

	@Autowired
	CategoryMapper categoryMapper;

	/*
	 * @param 게시판 id
	 * @return 해당 게시판의 카테고리, 없으면 null(게시판에 카테고리가 없을 수 있으므로 null 허용함)
	 */
	@Override
	public List<Category> findAllByBoardId(int boardId) {
		return categoryMapper.findAllByBoardId(boardId);
	}

	/*
	 * @param 게시판 id, 카테고리 이름
	 */
	@Override
	public void insert(CategoryInsertRequestDto category) {
		categoryMapper.insert(category);
	}

	/*
	 * @param 카테고리 id, 카테고리 이름
	 * @return
	 */
	@Override
	public void update(int id, CategoryUpdateRequestDto category) {
		categoryMapper.update(id, category);
	}

	/*
	 * @param
	 * @return
	 */
	@Override
	public void delete(int id) {
		// TODO delete 메소드 작성해야 함

	}

}
