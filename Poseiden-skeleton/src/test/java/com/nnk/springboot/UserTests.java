package com.nnk.springboot;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserService;

@SpringBootTest
public class UserTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void userListTest() {
		User user = new User("usertest@test.com", "Aa123456789.", "userfullnametest", "USER");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		
		//save
		userRepository.save(user);
		
		User usersaved = userRepository.findById(user.getId()).get();
		
		assertNotNull(usersaved.getId());
		assertEquals(usersaved.getUsername(), "usertest@test.com");
		assertEquals(usersaved.getFullname(), "userfullnametest");
		assertEquals(usersaved.getRole(), "USER");
		
		//update
		usersaved.setFullname("userfullnameupdated");
		userRepository.save(usersaved);
		
		User userupdated = userRepository.findById(user.getId()).get();
		assertEquals(userupdated.getFullname(), "userfullnameupdated");
		
		//delete
		userRepository.delete(userupdated);
		Optional<User> userdeleted = userRepository.findById(user.getId());
		assertFalse(userdeleted.isPresent());
	}

}
