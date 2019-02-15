package kr.nutee.service;

import kr.nutee.dao.User;
import kr.nutee.model.user.UserUpdateModel;

public interface UserService {
	public abstract User login(final String nickname, final String pw);
	public abstract User findSign(final String studentNumber, final String email);
	public abstract void delete(final int id);
	public abstract void update(final UserUpdateModel user);
	public abstract User findOne(int id);
}
