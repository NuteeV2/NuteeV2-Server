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
import kr.nutee.model.CustomResponseBody;
import kr.nutee.service.CommentService;

@RestController
@RequestMapping("comment")
public class CommentController {
	
	private CommentService commentService;

	@Autowired
	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@PostMapping("create")
	public ResponseEntity<CustomResponseBody> create(@Valid final Comment comment) {
		commentService.create(comment);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.CREATED);
	}
	
	@PutMapping("update")
	public ResponseEntity<CustomResponseBody> update(@Valid final Comment comment) {
		commentService.update(comment);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
	}
	
	@PatchMapping("delete/{id}")
	public ResponseEntity<CustomResponseBody> delete(@PathVariable("id") int id) {
		commentService.delete(id);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NO_CONTENT);
	}
}
