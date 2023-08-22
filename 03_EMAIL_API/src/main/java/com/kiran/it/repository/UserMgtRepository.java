package com.kiran.it.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiran.it.entity.UserMaster;

public interface UserMgtRepository extends JpaRepository<UserMaster,Integer>{

}
