package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.repository.UserRepository;
import com.example.services.UserService;
import com.example.models.User;

@RestController
public class UserController {
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		
		List<User> users = userRepository.findAll();
		
		return users;
	}
	
	@GetMapping("/api/users/{userId}")
	public User getUsersById(@PathVariable("userId") Integer Id) throws Exception {
		
		User user = userService.findUserById(Id);
		
		return user;
		
	}
	
	
	
	@PutMapping("/api/users")
	public User updateUser(@RequestBody User user,@RequestHeader("Authorization")String jwt) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser(user, reqUser.getId());
		
		return updatedUser;
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws Exception {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User user = userService.followUser(reqUser.getId(), userId2);
		
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		
		List<User> users = userService.searchUser(query);
		
		return users;
		
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		
		User user = userService.findUserByJwt(jwt);
		return user;
	}
	
	@DeleteMapping("/api/users/delete")
	public String deleteUser(@RequestHeader("Authorization")String jwt) throws Exception {
		User user = userService.findUserByJwt(jwt);
		return userService.deleteUser(user.getId());
		
	}
}
