package com.example.services;

import java.util.List;

import com.example.models.Story;
import com.example.models.User;

public interface StoriesService {
	
	public Story createStory(Story story, User user);
	
	public List<Story> findStoryByUserId(Integer userId) throws Exception;
	
	public List<Story> findAllStories();

}
