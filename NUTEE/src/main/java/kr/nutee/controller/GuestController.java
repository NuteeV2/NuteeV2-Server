package kr.nutee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dao.User;
import kr.nutee.service.GuestService;
import kr.nutee.service.UserService;


/**
 * GuestController class
 */
@RestController
@RequestMapping("guest")
public class GuestController {

	private final GuestService guestService;
	private final UserService userService;

	@Autowired
	public GuestController(GuestService guestService, UserService userService) {
		this.guestService = guestService;
		this.userService = userService;
	}

	//TODO 플젝 완성 후 삭제
	@GetMapping("index")
	public String index() {
		return "main page";
	}

	//TODO 플젝 완성 후 삭제
	@GetMapping("success")
	public String seccess() {
		return "login seccess";
	}

	//TODO 플젝 완성 후 삭제
	@GetMapping("error")
	public String error() {
		return "login error";
	}

	/**
	 * Login
	 *
	 * @param nickname
	 * @param pw
	 * @return ResponseEntity<String>
	 */
	@PostMapping("login")
	public ResponseEntity<String> login(final String nickname, final String pw) {
		User user = guestService.login(nickname, pw);
		if (user == null) {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Create a user
	 *
	 * @param User nickname, pw, studentNumber, roleId, coution and email
	 * @return ResponseEntity<String>
	 */
	@PostMapping("signUp")
	public ResponseEntity<String> signUp(@Valid final User user) {
		guestService.signUp(user);
		return new ResponseEntity<>("", HttpStatus.CREATED);
	}

	/**
	 * Find a user information
	 *
	 * @param studentNumber
	 * @param email
	 * @return ResponseEntity<String>
	 */
	@PostMapping("findSign")
	public ResponseEntity<String> findSign(final String studentNumber, final String email) {
		User user = userService.findSign(studentNumber, email);
		if (user == null)
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>("", HttpStatus.OK);
	}
}
