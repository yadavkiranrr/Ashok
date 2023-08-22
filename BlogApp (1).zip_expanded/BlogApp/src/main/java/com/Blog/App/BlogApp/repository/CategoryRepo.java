package com.Blog.App.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Blog.App.BlogApp.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
