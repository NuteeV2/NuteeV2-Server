package kr.nutee.service;

import kr.nutee.dao.User;
import kr.nutee.model.user.UserUpdateModel;

/**
 * UserService interface
 */
public interface UserService {
	
	/**
	 * @param nickname
	 * @param pw
	 * @return User
	 */
	public abstract User login(final String nickname, final String pw);
	
	/**
	 * @param studentNumber
	 * @param email
	 * @return User
	 */
	public abstract User findSign(final String studentNumber, final String email);
	
	/**
	 * @param id user ID
	 */
	public abstract void delete(final int id);
	
	/**
	 * @param UserUpdateModel id, nickname, pw and email
	 */
	public abstract void update(final UserUpdateModel user);
	
	/**
	 * @param id user ID
	 * @return User
	 */
	public abstract User findOne(int id);
}
