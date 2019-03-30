package kr.nutee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.model.Category.CategoryInsertRequestDto;
import kr.nutee.model.Category.CategoryListResponseDto;
import kr.nutee.service.impl.CategoryServicecImpl;

/*
 * Category Controller
 *
 * @author choiyk
 */
@RestController
public class CategoryController {

		private final CategoryServicecImpl categoryService;

		@Autowired
		public CategoryController(CategoryServicecImpl categoryService) {
			this.categoryService = categoryService;
		}

		/*
		 * Board id에 해당하는 Category List 출력
		 * @param 게시판  id
		 * @return ResponseEntity<CustomResponseBody>
		 */
		@GetMapping("boards/{boardId}/categories")
		public ResponseEntity<List<CategoryListResponseDto>> categories(@PathVariable("boardId") int boardId){
			return new ResponseEntity<>(categoryService.findAllByBoardId(boardId), HttpStatus.OK);
		}

		/*
		 * 게시판에 카테고리 추가
		 * @param 게시판 id, 카테고리 이름
		 * @return ResponseEntity<CustomResponseBody>
		 */
		@PostMapping("boards/{boardId}/categories/{categoryName:.+}")
		public ResponseEntity<String> insert(@PathVariable("boardId") int boardId,
				@PathVariable String categoryName){
			CategoryInsertRequestDto category = new CategoryInsertRequestDto();
			category.setBoardId(boardId);
			category.setCategoryName(categoryName);
			categoryService.insert(category);
			return new ResponseEntity<>("", HttpStatus.CREATED);
		}

		/*
		 * 카테고리 이름 변경
		 * @param 변경할 카테고리 id, 게시판 id, 카테고리 이름
		 * @return ResponseEntity<CustomResponseBody>
		 */
		@PatchMapping("categories/{id}/{categoryName:.+}")
		public ResponseEntity<String> update(@PathVariable("id") int id,
				@PathVariable String categoryName){
			categoryService.update(id, categoryName);
			return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
		}

		/*
		 * 카테고리 삭제
		 * @param 카테고리 id
		 */
		@DeleteMapping("categories/{id}")
		public ResponseEntity<String> delete(@PathVariable("id") int id){
			categoryService.delete(id);
			return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
		}

}
