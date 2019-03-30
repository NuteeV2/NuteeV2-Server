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
import kr.nutee.service.CommentService;

/**
 * CommentController class
 */
@RestController
@RequestMapping("comment")
public class CommentController {

	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	/**
	 * Create a comment
	 *
	 * @param Comment contents, userId, anonymous and articleId
	 * @return ResponseEntity<String>
	 */
	@PostMapping("create")
	public ResponseEntity<String> create(@Valid final Comment comment) {
		commentService.create(comment);
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

	/**
	 * Update a comment
	 *
	 * @param Comment id, contents, userId, anonymous and articleId
	 * @return ResponseEntity<String>
	 */
	@PutMapping("update")
	public ResponseEntity<String> update(@Valid final Comment comment) {
		commentService.update(comment);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Patch a comment
	 *
	 * @param id comment ID to be deleted
	 * @return ResponseEntity<String>
	 */
	@PatchMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") int id) {
		commentService.delete(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
