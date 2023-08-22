package com.kiran.it.service.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.kiran.it.bindings.ActivateAccount;
import com.kiran.it.bindings.Login;
import com.kiran.it.bindings.User;
import com.kiran.it.entity.UserMaster;
import com.kiran.it.repository.UserMgtRepository;
import com.kiran.it.service.UserMgtService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Service
public class UserMgtServiceImpl implements UserMgtService {

	@Autowired
	private UserMgtRepository userRepo;

	@Override
	public boolean saveUser(User user) {

		UserMaster entity = new UserMaster();
		BeanUtils.copyProperties(user, entity);

		entity.setPassword(generatedRandomPwd());
		entity.setAccStatus("In-Active");

		UserMaster save = userRepo.save(entity);

		String subject = "Your Registration Success";

		String body = readEmailBody(entity.getFullname(), entity.getPassword());

		emailUtils.sendEmail(user.getEmail(), subject, body);

		return save.getUserId() != null;
	}

	@Override
	public boolean activateUserAcc(ActivateAccount activeAcc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAllUsers() {
		List<UserMaster> findAll = userRepo.findAll();

		List<User> users = new ArrayList<>();

		for (UserMaster entity : findAll) {

			User user = new User();

			BeanUtils.copyProperties(entity, user);

			users.add(user);

		}

		return users;
	}

	@Override
	public User getUserByID(Integer userId) {

		Optional<UserMaster> findById = userRepo.findById(userId);

		if (findById.isPresent()) {

			User user = new User();

			UserMaster userMaster = findById.get();

			BeanUtils.copyProperties(userMaster, user);

			return user;

		}
		return null;
	}
	

	@Override
	public boolean deleteUserById(Integer userId) {
		try {

			userRepo.deleteById(userId);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean changeAccountStatus(Integer userId, String accStatus) {
		Optional<UserMaster> findById = userRepo.findById(userId);
		
		if (findById.isPresent()) {
			
			UserMaster userMaster = findById.get();
			userMaster.setAccStatus(accStatus);
			userRepo.save(userMaster);
			return true;
			
		}
		return false;
	}

	@Override
	public String login(Login login) {
		
		UserMaster entity=new UserMaster();
		
		entity.setEmail(login.getEmail());
		
		entity.setPassword(login.getPassword());
		
		Example<UserMaster> of = Example.of(entity);
		
		List<UserMaster> findAll = userRepo.findAll(of);
		
		if (findAll.isEmpty()) {
			
			return "Invalid Credentials";
		}
		else {
			
			UserMaster userMaster = findAll.get(0);
			
			if (userMaster.getAccStatus().equals("Active")) {
				
				return "SUCCESS";
				
			}
			else {
				
				return "Account not activated";
			}
		}
	
	}
	
	private String generatedRandomPwd() {
		
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		String lowerAlphabet = "abcdefghijklmnopqrstuvwwxya";
		
		String numbers= "0123456789";
		
		String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
		
		StringBuilder sb=new StringBuilder();
		
		Random random= new Random();
		
		
	}

}
