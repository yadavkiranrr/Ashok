package com.Blog.App.BlogApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.App.BlogApp.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
