package kr.nutee.service;


import kr.nutee.dao.Comment;

/**
 * CommentService interface
 */
public interface CommentService {
	
	/**
	 * @param Comment contents, userId, anonymous and articleId
	 */
	public abstract void create(final Comment comment);
	
	/**
	 * @param Comment contents, userId, anonymous and articleId
	 */
	public abstract void update(final Comment comment);
	
	/**
	 * @param id user ID
	 */
	public abstract void delete(final int id);
}
