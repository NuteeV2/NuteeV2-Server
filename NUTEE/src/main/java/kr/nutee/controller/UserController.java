package kr.nutee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.nutee.dao.User;
import kr.nutee.model.user.UserUpdateModel;
import kr.nutee.service.UserService;


/**
 * UserController class
 */
@RestController
@RequestMapping("user")
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
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
		User user = userService.login(nickname, pw);
		if (user == null)
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>("", HttpStatus.OK);
	}


	/**
	 * find a user
	 *
	 * @param id user ID
	 * @return ResponseEntity<User>
	 */
	@GetMapping("findOne/{id}")
	public ResponseEntity<User> findOne(@PathVariable("id") final int id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/**
	 * Update a user
	 *
	 * @param UserUpdateModel id, nickname, pw and email
	 * @return ResponseEntity<String>
	 */
	@PutMapping("update")
	public ResponseEntity<String> update(@Valid final UserUpdateModel user) {
		userService.update(user);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Patch a user
	 *
	 * @param id user ID
	 * @return ResponseEntity<String>
	 */
	@PatchMapping("delete/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") final int id) {
		userService.delete(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	/**
	 * Logout
	 *
	 * @return ResponseEntity<String>
	 */
	@GetMapping("logout")
	public ResponseEntity<String> logout() {
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
