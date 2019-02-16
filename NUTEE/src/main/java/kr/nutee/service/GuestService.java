package kr.nutee.service;

import kr.nutee.dao.User;

/**
 * GuestService interface
 */
public interface GuestService {
	
	/**
	 * @param nickname
	 * @param pw
	 * @return User
	 */
	public abstract User login(final String nickname, final String pw);
	
	/**
	 * @param user
	 */
	public abstract void signUp(final User user);
}
