package com.Blog.App.BlogApp.service.impl;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Blog.App.BlogApp.entity.Category;
import com.Blog.App.BlogApp.entity.Post;
import com.Blog.App.BlogApp.entity.User;
import com.Blog.App.BlogApp.exception.ResourceNotFoundException;
import com.Blog.App.BlogApp.payload.PostDto;
import com.Blog.App.BlogApp.payload.PostResponse;
import com.Blog.App.BlogApp.repository.CategoryRepo;
import com.Blog.App.BlogApp.repository.PostRepo;
import com.Blog.App.BlogApp.repository.UserRepo;
import com.Blog.App.BlogApp.service.PostService;

@Service
public class PostImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("userid","userid", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","categoryId", categoryId));
		
		
		 Post post=this.modelMapper.map(postDto, Post.class);
		
		post.setImagename("default.jpg");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","postId", postId));
		post.setContent(postdto.getContent());
		post.setImagename(postdto.getImageName());
		post.setTitle(postdto.getTitle());
	   Post updatedPost=this.postRepo.save(post);
		 
	   
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("postId", "postId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		// TODO Auto-generated method stub
		
		Sort sort=(sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
//		
//		if (sortDir.equalsIgnoreCase("asc")) {
//			
//			sort=Sort.by(sortBy).ascending();
//		}
//		else {
//			sort=Sort.by(sortBy).descending();
//		}
//		
		Pageable p=PageRequest.of(pageNumber,pageSize,sort);
		
		Page<Post> pagePost=this.postRepo.findAll(p);
		
		List<Post> allPosts= pagePost.getContent();
		List<PostDto> postdtos=allPosts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postdtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "postid", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryId", categoryId));
		
		List<Post> posts=this.postRepo.findByCategory(cat);
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("userId", "user", userId));
	   
		List<Post> posts=this.postRepo.findByUser(user);
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		List<Post> posts=this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
