package com.Blog.App.BlogApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.App.BlogApp.payload.ApiResponse;
import com.Blog.App.BlogApp.payload.CategoryDto;
import com.Blog.App.BlogApp.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto ){
		
		CategoryDto categoryDto1=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto1,HttpStatus.CREATED);
	}
	
	
	//get
	@GetMapping("/{catid}")
public ResponseEntity<CategoryDto> getById(@PathVariable Integer catid){
		
		CategoryDto categoryDto1=this.categoryService.getById(catid);
		return new ResponseEntity<CategoryDto>(categoryDto1,HttpStatus.OK);
	}
	
	
	//update
@PutMapping("/{catid}")
public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable ("catid") Integer categoryId ){
	
	CategoryDto updatedCategory=this.categoryService.updateCategory(categoryId, categoryDto);
	return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.CREATED);
}
	
	//delete
@DeleteMapping("/{catid}")
public ResponseEntity<ApiResponse> deleteCategory(@PathVariable ("catid") Integer categoryId ){
	
	this.categoryService.deleteCategory(categoryId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted success!!!",false),HttpStatus.OK);
}
	
	//all
@GetMapping("/all")
 public ResponseEntity<List<CategoryDto>> allCategory(){
	 
	 List<CategoryDto> categoryDto=this.categoryService.allCategory();
	 
	 return ResponseEntity.ok(categoryDto);
	 
 }
 
 
}
