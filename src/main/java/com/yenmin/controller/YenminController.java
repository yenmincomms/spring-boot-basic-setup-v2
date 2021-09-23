package com.yenmin.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yenmin.exception.YenminException;
import com.yenmin.model.ResponseMessage;
import com.yenmin.model.entity.User;
import com.yenmin.service.IUserService;

@RestController
@RequestMapping("/manage/v1/users")
public class YenminController {
	@Autowired
	IUserService userService;

	@PostMapping("/create")
	public Object createUser(@RequestBody @Valid User userDetails) throws YenminException {
		return userService.createUserDetails(userDetails);

	}

	@GetMapping("/getAllUsers")
	public List<User> getAllUsers(@RequestParam Optional<Integer> pageIdx, @RequestParam Optional<Integer> pageSize,
			@RequestParam Optional<String> sortKey, @RequestParam Optional<String> orderDirection)
			throws YenminException {

		return userService.getAllUsers(pageIdx, pageSize, sortKey, orderDirection);

	}

	@GetMapping("/{userSSO}")
	public ResponseEntity<User> getUserGeoLocationBySSO(@PathVariable(value = "userSSO") final String userSSO)
			throws YenminException {
		if (userSSO != null && !userSSO.trim().equals("")) {
			return new ResponseEntity<>(userService.getUserById(userSSO), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{userSSO}")
	public ResponseEntity<ResponseMessage> deleteUserBySSO(@PathVariable(value = "userSSO") final String userSSO)
			throws YenminException {
		if (userSSO != null && !userSSO.trim().equals("")) {
			return new ResponseEntity<>(userService.deleteUserById(userSSO), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
