package kr.nutee.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.User;
import kr.nutee.model.user.UserUpdateModel;

@Mapper
public interface UserMapper {
	public abstract User login(final String nickname, final String pw);
	public abstract User findSign(final String studentNumber, final String email);
	public abstract void delete(final int id);
	public abstract void update(final UserUpdateModel user);
	public abstract User findOne(int id);
}
