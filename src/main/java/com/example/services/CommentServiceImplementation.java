package com.example.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.aspectj.weaver.patterns.IScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Comment;
import com.example.models.Post;
import com.example.models.User;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;

@Service
public class CommentServiceImplementation implements CommentService{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Comment createComments(Comment comment, Integer postId, Integer userId) throws Exception {
		
		User user = userService.findUserById(userId);
		Post post = postService.findPostById(postId);
		
		comment.setUser(user);
		comment.setContent(comment.getContent());
		comment.setCreatedAt(LocalDateTime.now());
		
		Comment savedComment = commentRepository.save(comment);
		
		post.getComments().add(savedComment);
		
		postRepository.save(post);
		
		return savedComment;
	}

	@Override
	public Comment likeComment(Integer commentId, Integer userId) throws Exception {
		Comment comment = findCommentById(commentId);
		User user = userService.findUserById(userId);
		
		if(!comment.getLikes().contains(user)) {
			comment.getLikes().add(user);
		}
		else {
			comment.getLikes().remove(user);	}
		
		return commentRepository.save(comment);
	}

	@Override
	public Comment findCommentById(Integer commentId) throws Exception {
		Optional<Comment> opt = commentRepository.findById(commentId);
		
		if (opt.isEmpty()) {
			throw new Exception("comment does not exist");
		}
		
		return opt.get();
	}
}
