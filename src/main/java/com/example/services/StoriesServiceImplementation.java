package com.example.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Story;
import com.example.models.User;
import com.example.repository.StoriesRepository;

@Service
public class StoriesServiceImplementation implements StoriesService{
	
	@Autowired
	private StoriesRepository storiesRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Story createStory(Story story, User user) {
		Story createdStory = new Story();
		createdStory.setCaption(story.getCaption());
		createdStory.setImage(story.getImage());
		createdStory.setUser(user);
		createdStory.setTimeStamp(LocalDateTime.now());
		return storiesRepository.save(createdStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception {
		User user = userService.findUserById(userId);
		
		return storiesRepository.findByUserId(userId);
	}

	@Override
	public List<Story> findAllStories() {
		
		return storiesRepository.findAll();		
	}

}
