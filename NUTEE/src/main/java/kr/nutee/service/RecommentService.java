package kr.nutee.service;

import kr.nutee.dao.Comment;

/**
 * RecommentService interface
 */
public interface RecommentService {

	/**
	 * @param Comment contents, userId, anonymous, recommentId and articleId
	 */
	public abstract void create(final Comment recomment);
	
	/**
	 * @param Comment id, contents, userId, anonymous, recommentID and articleId
	 */
	public abstract void update(final Comment comment);
	
	/**
	 * @param id comment ID
	 */
	public abstract void delete(final int id);
}
