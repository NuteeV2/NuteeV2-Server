package kr.nutee.service;


import kr.nutee.dao.Comment;

public interface CommentService {
	public abstract void create(final Comment comment);
	public abstract void update(final Comment comment);
	public abstract void delete(final int id);
}
