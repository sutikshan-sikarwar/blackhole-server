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
import com.example.models.Story;
import com.example.models.User;
import com.example.services.StoriesService;
import com.example.services.UserService;

@RestController
public class StoryController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StoriesService storiesService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story, @RequestHeader("Authorization") String jwt) {
		User reqUser = userService.findUserByJwt(jwt);
		Story createdStory = storiesService.createStory(story, reqUser);	
		return createdStory;
	}
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		List<Story> stories = storiesService.findStoryByUserId(userId);	
		return stories;
	}
	
	@GetMapping("/api/story")
	public List<Story> findAllReels() {
		
		List<Story> stories = storiesService.findAllStories();
		
		return stories;
	}

}
