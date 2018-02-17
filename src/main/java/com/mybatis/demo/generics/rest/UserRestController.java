package com.mybatis.demo.generics.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mybatis.demo.generics.model.User;
import com.mybatis.demo.generics.service.UserService;


@RestController
@RequestMapping("/mybatis/user")
public class UserRestController {

	// *************************************************************************************************
	// Constants
	private final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

	// *************************************************************************************************
	// member variables
	private UserService userService;

	// *************************************************************************************************
	// Constructors
	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	// Implementation
	@RequestMapping(value = "/id/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> getUser(@PathVariable Integer userId) {
		LOG.info("findUser: {}", userId);
		User user = userService.selectUserById(userId);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllUser() {
		LOG.info("findAllUser:");
		return new ResponseEntity<>(
				(userService.selectAllUsers()),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/id/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
		LOG.info("deletUser: {}", userId);
		userService.deleteUser(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	

}
