package kr.nutee.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.Comment;

@Mapper
public interface CommentMapper {
	public abstract void create(final Comment comment);
	public abstract void update(final Comment comment);
	public abstract void delete(final int id);
}
