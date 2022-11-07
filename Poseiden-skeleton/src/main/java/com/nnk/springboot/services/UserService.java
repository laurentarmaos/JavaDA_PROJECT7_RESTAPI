package com.nnk.springboot.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.nnk.springboot.domain.User;

public interface UserService extends UserDetailsService{
	
	List<User> getUserList();
	
	User getById(Integer id);
	
	boolean existsByUserName(String userName);
	
	User addUser(User dto);

	User createUser(User dto);
	
	User updateUser(User dto, Integer id);
	
	void deleteUser(Integer id) throws Exception;
}
