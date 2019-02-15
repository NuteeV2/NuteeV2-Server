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

	@GetMapping("index")
	public String index() {
		return "main page";
	}

	@GetMapping("success")
	public String seccess() {
		return "login seccess";
	}
	
	@GetMapping("error")
	public String error() {
		return "login error";
	}

	@PostMapping("login")
	public ResponseEntity<CustomResponseBody> login(final String nickname, final String pw) {
		User user = guestService.login(nickname, pw);
		CustomResponseBody body = new CustomResponseBody();
		if (user == null) {
			System.out.println("controllernull");
			return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
		} else
			return new ResponseEntity<>(body, HttpStatus.OK);
	}

	@PostMapping("signUp")
	public ResponseEntity<CustomResponseBody> signUp(@Valid final User user) {
		guestService.signUp(user);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<CustomResponseBody>(body, HttpStatus.CREATED);
	}

	@PostMapping("findSign")
	public ResponseEntity<CustomResponseBody> findSign(final String studentNumber, final String email) {
		User user = userService.findSign(studentNumber, email);
		CustomResponseBody body = new CustomResponseBody();
		if (user == null)
			return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
		else
			return new ResponseEntity<>(body, HttpStatus.OK);
	}
}
