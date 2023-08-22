package com.Blog.App.BlogApp.service;

import org.springframework.stereotype.Service;

import com.Blog.App.BlogApp.payload.CommentDto;

@Service
public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);

}
