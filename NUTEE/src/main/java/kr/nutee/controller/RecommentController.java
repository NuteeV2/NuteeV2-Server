package kr.nutee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dao.Comment;
import kr.nutee.service.RecommentService;

/**
 * RecommentController class
 */
@RestController
@RequestMapping("recomment")
public class RecommentController {

	private RecommentService recommentService;

	@Autowired
	public RecommentController(RecommentService recommentService) {
		this.recommentService = recommentService;
	}

	/**
	 * Create a recomment
	 *
	 * @param Comment contents, userId, anonymous, recommentId and articleId
	 * @return ResponseEntity<String>
	 */
	@PostMapping("create")
	public ResponseEntity<String> create(@Valid final Comment recomment) {
		System.out.println(recomment.getRecommentId());
		recommentService.create(recomment);
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

	/**
	 * Update a recomment
	 *
	 * @param Comment id, contents, userId, anonymous, recommentId and articleId
	 * @return ResponseEntity<String>
	 */
	@PutMapping("update")
	public ResponseEntity<String> update(@Valid final Comment recomment) {
		recommentService.update(recomment);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Patch a recomment
	 *
	 * @param id comment ID to be deleted
	 * @return ResponseEntity<String>
	 */
	@PatchMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		recommentService.delete(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
