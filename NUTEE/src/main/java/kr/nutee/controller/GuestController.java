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
import kr.nutee.model.CustomResponseBody;
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
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PostMapping("login")
	public ResponseEntity<CustomResponseBody> login(final String nickname, final String pw) {
		User user = guestService.login(nickname, pw);
		CustomResponseBody body = new CustomResponseBody();
		if (user == null) {
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/**
	 * Create a user
	 * 
	 * @param User nickname, pw, studentNumber, roleId, coution and email
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PostMapping("signUp")
	public ResponseEntity<CustomResponseBody> signUp(@Valid final User user) {
		guestService.signUp(user);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.CREATED);
	}

	/**
	 * Find a user information
	 * 
	 * @param studentNumber
	 * @param email
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PostMapping("findSign")
	public ResponseEntity<CustomResponseBody> findSign(final String studentNumber, final String email) {
		User user = userService.findSign(studentNumber, email);
		CustomResponseBody body = new CustomResponseBody();
		if (user == null)
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(body, HttpStatus.OK);
	}
}
