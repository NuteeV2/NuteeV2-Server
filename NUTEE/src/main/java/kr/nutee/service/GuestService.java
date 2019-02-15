package kr.nutee.service;

import kr.nutee.dao.User;

public interface GuestService {
	public abstract User login(final String nickname, final String pw);
	public abstract void signUp(final User user);
}
