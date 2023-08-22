package com.Blog.App.BlogApp.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.App.BlogApp.entity.Comment;
import com.Blog.App.BlogApp.entity.Post;
import com.Blog.App.BlogApp.exception.ResourceNotFoundException;
import com.Blog.App.BlogApp.payload.CommentDto;
import com.Blog.App.BlogApp.repository.CommentRepo;
import com.Blog.App.BlogApp.repository.PostRepo;
import com.Blog.App.BlogApp.service.CommentService;

@Service
public class CommentImpl implements
CommentService
{
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId", postId));
		
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
	
		Comment savedcomment = this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedcomment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		
		Comment com=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","commentId", commentId));
		
		this.commentRepo.delete(com);
		
	}

}
