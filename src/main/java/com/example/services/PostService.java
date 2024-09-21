package com.example.services;

import java.util.List;

import com.example.models.Post;

public interface PostService {
	
	Post createNewPost(Post post,Integer userId) throws Exception;
	
	String deletePost(Integer postId, Integer userId) throws Exception;
	
	List<Post> findPostByUserId(Integer userId);
	
	Post findPostById(Integer postId) throws Exception;
	
	List<Post> findAllPosts();
	
	Post savedPost(Integer postId, Integer userId) throws Exception;
	
	Post likedPost(Integer postId, Integer userId) throws Exception;

}
