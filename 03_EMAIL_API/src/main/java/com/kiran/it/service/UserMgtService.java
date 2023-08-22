package com.kiran.it.service;

import java.util.List;

import com.kiran.it.bindings.ActivateAccount;
import com.kiran.it.bindings.Login;
import com.kiran.it.bindings.User;

public interface UserMgtService {
	
	boolean saveUser(User user);
	
	boolean activateUserAcc(ActivateAccount activeAcc);
	
	List<User> getAllUsers();
	
	User getUserByID(Integer userId);
	
	boolean deleteUserById(Integer userId);
	
	boolean changeAccountStatus(Integer userId,String accStatus);
	
	String login(Login login);
	
	

}
