package com.nnk.springboot.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	Logger log = LoggerFactory.getLogger(BidListController.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getUserList() {
		
		return userRepository.findAll();
	}

	@Override
	public User getById(Integer id) {

		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		
		return user;
	}
	
	@Override
	public boolean existsByUserName(String userName) {

		return userRepository.existsByUsername(userName);
	}

	@Override
	public User addUser(User dto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		dto.setPassword(encoder.encode(dto.getPassword()));
		
		log.info("Service : user :" + dto.getUsername() + " created by administrator");
		return userRepository.save(dto);
		
	}

	@Override
	public User createUser(User dto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		dto.setPassword(encoder.encode(dto.getPassword()));
		dto.setRole("USER");
		log.info("Service : user :" + dto.getUsername() + " created by registration");
		return userRepository.save(dto);
		
	}
	
	@Override
	public User updateUser(User dto, Integer id) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		dto.setId(id);
		
		dto.setPassword(encoder.encode(dto.getPassword()));
		
		log.info("Service : user :" + dto.getUsername() + " updated by administrator");
		return userRepository.save(dto);
		
	}

	@Override
	public void deleteUser(Integer id) throws Exception {
		
		userRepository.deleteById(id);
		log.info("Service : user with id :" + id + " deleted by administrator");
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

        if (user == null){
        	log.error("Service : error : " + username + " not found");
            throw new UsernameNotFoundException("Invalid username.");
        }
        
        
        UserDetails userdetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole()).build();
        
        return userdetails;
	}

}
