package kr.nutee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.nutee.dao.Comment;
import kr.nutee.repository.mapper.CommentMapper;
import kr.nutee.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentMapper commentMapper;
	
	@Autowired
	public CommentServiceImpl(final CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	@Override
	public void create(final Comment comment) {
		commentMapper.create(comment);
	}

	@Override
	public void update(Comment comment) {
		commentMapper.update(comment);
	}

	@Override
	public void delete(int id) {
		commentMapper.delete(id);
	}

}
