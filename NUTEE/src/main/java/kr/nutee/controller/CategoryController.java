package kr.nutee.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.model.CustomResponseBody;
import kr.nutee.model.Category.CategoryInsertRequestDto;
import kr.nutee.model.Category.CategoryUpdateRequestDto;
import kr.nutee.service.impl.CategoryServicecImpl;

/*
 * Category Controller
 *
 * @author choiyk
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	//Logging
		private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
		@GetMapping("{boardId}")
		public ResponseEntity<CustomResponseBody> categories(@PathVariable("boardId") int boardId){
			CustomResponseBody body = new CustomResponseBody(categoryService.findAllByBoardId(boardId), null);
			return new ResponseEntity<CustomResponseBody>(body, HttpStatus.OK);
		}

		/*
		 * 게시판에 카테고리 추가
		 * @param 게시판 id, 카테고리 이름
		 * @return ResponseEntity<CustomResponseBody>
		 */
		@PostMapping("")
		public ResponseEntity<CustomResponseBody> insert(@Valid @RequestBody CategoryInsertRequestDto category){
			categoryService.insert(category);
			CustomResponseBody body = new CustomResponseBody();
			return new ResponseEntity<CustomResponseBody>(body, HttpStatus.CREATED);
		}

		/*
		 * 카테고리 이름 변경
		 * @param 변경할 카테고리 id, 게시판 id, 카테고리 이름
		 * @return ResponseEntity<CustomResponseBody>
		 */
		@PatchMapping("{id}")
		public ResponseEntity<CustomResponseBody> update(@PathVariable("id") int id,
				@Valid @RequestBody CategoryUpdateRequestDto category){
			categoryService.update(id, category);
			CustomResponseBody body = new CustomResponseBody();
			return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
		}

}
