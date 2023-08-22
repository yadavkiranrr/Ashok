package com.Blog.App.BlogApp.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Blog.App.BlogApp.config.AppConstant;
import com.Blog.App.BlogApp.payload.ApiResponse;
import com.Blog.App.BlogApp.payload.PostDto;
import com.Blog.App.BlogApp.payload.PostResponse;
import com.Blog.App.BlogApp.service.FileService;
import com.Blog.App.BlogApp.service.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
    private PostService postService;
	
	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;
	
	//creates
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED); 
		
	}
	
	//get  posts by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>>getPostsByUser(@PathVariable Integer userId){
		
		List<PostDto> posts=this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	
	
	// get  by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		
		List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
		
	}
	
	
	//getAllPost
	@GetMapping("/post/allpost")
	public ResponseEntity<PostResponse> allPosts(@RequestParam(value="pageNumber",defaultValue=AppConstant.PAGE_NUMER,required=false) Integer pageNumber ,
			@RequestParam (value="pageSize",defaultValue=AppConstant.PAGE_SIZE,required=false)Integer pageSize,
			@RequestParam (value="sortBy",defaultValue=AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstant.SORT_DIR,required=false) String sortDir) {
		
		PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
		
	}
	
	//GetPostById
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto postDto=this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	}
	
	//delete
	@DeleteMapping("/post/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		
		this.postService.deletePost(postId);
		
		return new ApiResponse("Deleted Success",true);
		
		
	}
	
	//updatePost
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		
		PostDto updatedpost=this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatedpost, HttpStatus.OK);
	}
	
	//search
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(String keyword){
		
		List<PostDto> posts=this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
		
		
	}
	
	//post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		
		PostDto postDto=this.postService.getPostById(postId);
		
		String fileName=this.fileService.uploadImage(path, image);
	
		postDto.setImageName(fileName);
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	//method to serve files
	@GetMapping(value="/post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable ("imageName") String imageName,
			HttpServletResponse response) throws IOException{
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
		
	}
	
}
