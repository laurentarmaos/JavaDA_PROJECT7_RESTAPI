package com.nnk.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getUserList() {
		
		return userRepository.findAll();
	}

	@Override
	public User getById(Integer id) {

		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bid Id:" + id));
		
		return user;
	}

	@Override
	public void addUser(User dto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		dto.setPassword(encoder.encode(dto.getPassword()));

		userRepository.save(dto);
		
	}

	@Override
	public void createUser(User dto) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		dto.setPassword(encoder.encode(dto.getPassword()));
		dto.setRole("USER");

		userRepository.save(dto);
		
	}
	
	@Override
	public void updateUser(User dto, Integer id) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		dto.setId(id);
		
		dto.setPassword(encoder.encode(dto.getPassword()));
		
		userRepository.save(dto);
		
	}

	@Override
	public void deleteUser(Integer id) throws Exception {
		
		userRepository.deleteById(id);
		
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Invalid username or pwd.");
        }
        
        
        UserDetails userdetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole()).build();
        
        return userdetails;
	}

}
