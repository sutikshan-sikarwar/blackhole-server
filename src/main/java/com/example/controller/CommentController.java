package com.example.controller;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Comment;
import com.example.models.User;
import com.example.services.CommentService;
import com.example.services.UserService;

@RestController
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comments/posts/{postId}")
	public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,
			@PathVariable Integer postId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		Comment createdComment = commentService.createComments(comment, postId, user.getId());
		return createdComment;
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer commentId) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		Comment likedComment = commentService.likeComment(commentId, user.getId());
		return likedComment;
	}
	
	@GetMapping("comments/{commentId}")
	public Comment findComment(@PathVariable Integer commentId) throws Exception {
		Comment comment = commentService.findCommentById(commentId);
		return comment;
	}
}
 