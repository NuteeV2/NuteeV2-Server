package kr.nutee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import kr.nutee.dao.Category;
import kr.nutee.exception.OverlappedException;
import kr.nutee.model.Category.CategoryInsertRequestDto;
import kr.nutee.model.Category.CategoryListResponseDto;
import kr.nutee.model.Category.CategoryUpdateRequestDto;
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
	public List<CategoryListResponseDto> findAllByBoardId(int boardId) {
		List<Category> categories = categoryMapper.findAllByBoardId(boardId);
		if(categories == null) return null;	//TODO 리팩토링 필요
		List<CategoryListResponseDto> list = new ArrayList<>();
		for(Category c : categories) {
			CategoryListResponseDto dto = new CategoryListResponseDto();
			dto.setId(c.getId());
			dto.setCategoryName(c.getCategoryName());
			list.add(dto);
		}
		return list;
	}

	/*
	 * @param 카테고리 id
	 */
	@Override
	public Category findOne(int id) {
		return categoryMapper.findOne(id);
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
	 */
	@Override
	public void update(int id, CategoryUpdateRequestDto category) {
		try{
			categoryMapper.update(id, category);
		}
		catch(DataIntegrityViolationException e) {
			throw new OverlappedException("categoryName", "Category name already exist");
		}
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
