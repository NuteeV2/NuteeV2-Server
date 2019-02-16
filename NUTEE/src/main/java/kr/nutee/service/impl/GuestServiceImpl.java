package kr.nutee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import kr.nutee.dao.User;
import kr.nutee.exception.DuplicationException;
import kr.nutee.repository.mapper.GuestMapper;
import kr.nutee.service.GuestService;
import kr.nutee.util.EncryptionUtils;

/**
 * GuestService Implementation
 */
@Service
public class GuestServiceImpl implements GuestService {

	private final GuestMapper guestMapper;

	@Autowired
	public GuestServiceImpl(final GuestMapper guestMapper) {
		this.guestMapper = guestMapper;
	}

	@Override
	public User login(final String nickname, final String pw) {
		User user = guestMapper.login(nickname, EncryptionUtils.encryptSHA256(pw));
		if (user == null){
			return null;
		}else
			return user;
	}

	@Override
	public void signUp(User user) {
		user.setPw(EncryptionUtils.encryptSHA256(user.getPw()));
		try {
			guestMapper.signUp(user);
		} catch (DataIntegrityViolationException e) {
			String duplicationField = "";
			if (e.getMessage().contains("nickname_uq"))
				duplicationField = "nickname";
			else
				duplicationField = "studentNumber";
			throw new DuplicationException(duplicationField, duplicationField + " already exist");
		}
	}

}
