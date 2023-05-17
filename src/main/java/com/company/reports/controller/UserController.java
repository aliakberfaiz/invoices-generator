package com.company.reports.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.reports.service.UserService;
import com.company.reports.vo.CreateUserVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public String createUser(@RequestBody CreateUserVo userVo) {
		Integer userId = userService.createUserWithDetails(userVo);
		log.debug("User Created: {}", userId);
		return "User created successfully!" + userId; // TODO create a proper response
	}
}
