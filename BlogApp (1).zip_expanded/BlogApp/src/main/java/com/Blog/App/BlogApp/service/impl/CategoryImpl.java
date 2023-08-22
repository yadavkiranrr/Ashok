package com.Blog.App.BlogApp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.App.BlogApp.entity.Category;
import com.Blog.App.BlogApp.exception.ResourceNotFoundException;
import com.Blog.App.BlogApp.payload.CategoryDto;
import com.Blog.App.BlogApp.repository.CategoryRepo;
import com.Blog.App.BlogApp.service.CategoryService;

@Service
public class CategoryImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category=this.modelMapper.map(categoryDto,Category.class);
		Category addedCat=this.categoryRepo.save(category);
		
		return this.modelMapper.map(addedCat,CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryId", "category", categoryId));
		cat.setCategoryId(categoryDto.getCategoryId());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		Category updatedcat=this.categoryRepo.save(cat);
		
		return this.modelMapper.map(updatedcat,CategoryDto.class);
	}

	@Override
	public CategoryDto getById(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("categoryId","catagory", categoryId));
		return this.modelMapper.map(category,CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public List<CategoryDto> allCategory() {
		// TODO Auto-generated method stub
		List<Category> catagories=this.categoryRepo.findAll();
		List<CategoryDto> categorydtos=catagories.stream().map((cat)-> this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		return categorydtos;
	}

}
