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
import kr.nutee.model.CustomResponseBody;
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
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PostMapping("login")
	public ResponseEntity<CustomResponseBody> login(final String nickname, final String pw) {
		User user = userService.login(nickname, pw);
		CustomResponseBody body = new CustomResponseBody();
		if (user == null)
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	
	/**
	 * find a user
	 * 
	 * @param id user ID
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@GetMapping("findOne/{id}")
	public ResponseEntity<CustomResponseBody> findOne(@PathVariable("id") final int id) {
		User user = userService.findOne(id);
		if (user == null) {
			CustomResponseBody body = new CustomResponseBody();
			return new ResponseEntity<CustomResponseBody>(body, HttpStatus.NOT_FOUND);
		}
		CustomResponseBody body = new CustomResponseBody(user, null);
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	/**
	 * Update a user
	 * 
	 * @param UserUpdateModel nickname, pw and email
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PutMapping("update")
	public ResponseEntity<CustomResponseBody> update(@Valid final UserUpdateModel user) {
		userService.update(user);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/**
	 * Patch a user
	 * 
	 * @param id user ID
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@PatchMapping("delete/{id}")
	public ResponseEntity<CustomResponseBody> delete(@PathVariable("id") final int id) {
		userService.delete(id);
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

	/**
	 * Logout
	 * 
	 * @return ResponseEntity<CustomResponseBody>
	 */
	@GetMapping("logout")
	public ResponseEntity<CustomResponseBody> logout() {
		CustomResponseBody body = new CustomResponseBody();
		return new ResponseEntity<>(body, HttpStatus.OK);
	}

}
