package com.example.services;

import com.example.models.Comment;

public interface CommentService {

	public Comment createComments(Comment comment, Integer postId, Integer userId) throws Exception;
	
	public Comment likeComment(Integer commentId, Integer userId) throws Exception;
	
	public Comment findCommentById(Integer commentId) throws Exception;
	
}
