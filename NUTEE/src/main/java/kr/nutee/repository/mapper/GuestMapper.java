package kr.nutee.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.nutee.dao.User;

/**
 * GuestMapper interface
 */
@Mapper
public interface GuestMapper {
	
	/**
	 * @param User nickname, pw, studentNumber, roleId, coution and email
	 */
	public abstract void signUp(final User user);
	
	/**
	 * 
	 * @param nickname
	 * @param pw
	 * @return User
	 */
	public abstract User login(String nickname, String pw);
}
