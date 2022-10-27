package com.nnk.springboot.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nnk.springboot.domain.User;

public interface UserService extends UserDetailsService{
	
	List<User> getUserList();
	
	User getById(Integer id);
	
	void addUser(User dto);

	void createUser(User dto);
	
	void updateUser(User dto, Integer id);
	
	void deleteUser(Integer id) throws Exception;
}
