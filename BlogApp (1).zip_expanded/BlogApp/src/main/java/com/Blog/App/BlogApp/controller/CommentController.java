package com.Blog.App.BlogApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.App.BlogApp.payload.ApiResponse;
import com.Blog.App.BlogApp.payload.CommentDto;
import com.Blog.App.BlogApp.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentservice;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId)
	{
	
		CommentDto createComment= this.commentservice.createComment(commentDto, postId);
		
		return new ResponseEntity<CommentDto>(createComment,HttpStatus.ACCEPTED);
	}
	
	

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer postId)
	{
	
	 this.commentservice.deleteComment( postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment DeleteSuccess",true),HttpStatus.ACCEPTED);
	}
	

}
