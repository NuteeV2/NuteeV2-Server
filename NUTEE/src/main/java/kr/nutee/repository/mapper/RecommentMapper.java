package kr.nutee.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.Comment;

/**
 * RecommentMapper interface
 */
@Mapper
public interface RecommentMapper {
	
	/**
	 * @param Comment contents, userId, anonymous, recommentId and articleId
	 */
	public abstract void create(final Comment recomment);

	/**
	 * @param Comment id, contents, userId, anonymous, recommentId and articleId
	 */
	public abstract void update(final Comment recomment);

	/**
	 * @param id comment ID to be deleted
	 */
	public abstract void delete(final int id);
}
