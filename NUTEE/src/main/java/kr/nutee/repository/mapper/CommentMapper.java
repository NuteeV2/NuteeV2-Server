package kr.nutee.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.Comment;

/**
 * CommentMapper interface
 */
@Mapper
public interface CommentMapper {
	
	/**
	 * @param Comment contents, userId, anonymous and articleId
	 */
	public abstract void create(final Comment comment);
	
	/**
	 * @param Comment contents, userId, anonymous and articleId
	 */
	public abstract void update(final Comment comment);
	
	/**
	 * @param id customer ID to be deleted
	 */
	public abstract void delete(final int id);
}
