package com.example.services;

import java.util.List;

import com.example.models.Reels;
import com.example.models.User;

public interface ReelsService {
	
	public Reels createReels(Reels reel, User user);
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReel(Integer userId) throws Exception;

}
