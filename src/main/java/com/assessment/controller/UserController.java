package com.assessment.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.model.MyUserModel;
import com.assessment.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	private ResponseEntity<Boolean> createUser(@Valid @RequestBody MyUserModel myUserModel) {
		return ResponseEntity.ok().body(userService.creatingUser(myUserModel));
	}

}
