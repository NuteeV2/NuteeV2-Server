package kr.nutee.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.User;

@Mapper
public interface GuestMapper {
	public abstract void signUp(final User user);
	public abstract User login(String nickname, String pw);
}
