package com.macsupport.Services;

import org.springframework.stereotype.Service;

import com.macsupport.Models.Comment;
import com.macsupport.Repositories.CommentRepository;


@Service
public class CommentService {
private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public Comment addComment(Comment comment) {
		return this.commentRepository.save(comment);
	}
	public Comment findCommentByComment(String comment) {
		return commentRepository.findByComment(comment);
	}
}

