package kr.nutee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import kr.nutee.dao.User;
import kr.nutee.exception.DuplicationException;
import kr.nutee.model.user.UserUpdateModel;
import kr.nutee.repository.mapper.UserMapper;
import kr.nutee.service.MailService;
import kr.nutee.service.UserService;
import kr.nutee.util.EncryptionUtils;
import kr.nutee.util.Random;

/**
 * UserService Implementation
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(final String nickname, final String pw) {
		User user = userMapper.login(nickname, EncryptionUtils.encryptSHA256(pw));
		if (user == null) {
			return null;
		} else
			return user;
	}

	@Override
	public User findSign(String studentNumber, String email) {
		User user = userMapper.findSign(studentNumber, email);
		String newPassword;

		if (user == null)
			return null;
		else {
			UserUpdateModel userModel = new UserUpdateModel(user.getId(), user.getNickname(), user.getPw(), user.getEmail());
			newPassword = Random.makeRandomNumber();
			userModel.setPw(newPassword);
			MailService.gmailSend(userModel);
			userModel.setPw(EncryptionUtils.encryptSHA256(newPassword));
			userMapper.update(userModel);
			return user;
		}
	}
	
	@Override
	public User findOne(int id) {
		return userMapper.findOne(id);
	}

	@Override
	public void update(UserUpdateModel user) {
		try {
			userMapper.update(user);
		} catch (DataIntegrityViolationException e) {
			String duplicationField = "";
			if (e.getMessage().contains("nickname_uq"))
				duplicationField = "nickname";
			else
				duplicationField = "email";
			throw new DuplicationException(duplicationField, duplicationField + " already exist");
		}
	}

	@Override
	public void delete(final int id) {
		userMapper.delete(id);
	}

}
