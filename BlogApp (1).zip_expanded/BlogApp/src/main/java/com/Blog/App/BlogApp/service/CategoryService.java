package com.Blog.App.BlogApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Blog.App.BlogApp.payload.CategoryDto;

@Service
public interface CategoryService {
	
	//create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	
	//update
	
	CategoryDto updateCategory(Integer categoryId,CategoryDto categoryDto);
	
	
	//get
	
	CategoryDto getById(Integer categoryId);
	
	//delete
	void deleteCategory(Integer categoryId);
	
	//getall
	
	List<CategoryDto> allCategory();
	

}
