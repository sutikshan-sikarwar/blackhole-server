package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Reels;
import com.example.models.User;
import com.example.services.ReelsService;
import com.example.services.UserService;

@RestController
public class ReelsController {
	
	@Autowired
	private ReelsService reelsService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) {
		
		User reqUser = userService.findUserByJwt(jwt);
		
		Reels createdReel = reelsService.createReels(reel, reqUser);
		
		return createdReel;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		
		List<Reels> reels = reelsService.findAllReels();
		
		return reels;
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
		
		List<Reels> reels = reelsService.findUserReel(userId);
		
		return reels;
	}

}
