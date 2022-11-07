package com.nnk.springboot.servicetests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserServiceImpl userService;
	
	@Test
	void testSaveRating() {
		User user = new User(1, "usertest@test.com", "Aa123456789.", "userfullnametest", "USER");
		
		given(userRepository.save(user)).willReturn(user);
		
		User userSaved = userService.createUser(user);
		
		assertThat(userSaved).isNotNull();
		assertEquals(user, userSaved);
		verify(userRepository).save(any(User.class));
		
		
		User userAdded = userService.addUser(user);
		
		assertThat(userAdded).isNotNull();
		assertEquals(user, userAdded);
	}
	
	@Test
	void testUserExsists() {
		User user = new User(1, "usertest@test.com", "Aa123456789.", "userfullnametest", "USER");
		
		given(userRepository.existsByUsername(user.getUsername())).willReturn(true);
		
		assertTrue(userService.existsByUserName(user.getUsername()));
		
		
		String noUser = null;
		
		given(userRepository.existsByUsername(noUser)).willReturn(false);
		
		assertFalse(userService.existsByUserName(noUser));
	}
	
	@Test
	void testUpdateRating() {
		User user = new User(1, "usertest@test.com", "Aa123456789.", "userfullnametest", "USER");
		
		given(userRepository.save(user)).willReturn(user);
		
		User userUpdated = userService.updateUser(user, 1);
		
		assertThat(userUpdated).isNotNull();
		assertEquals(user, userUpdated);
		verify(userRepository).save(any(User.class));
	}
	
	@Test
	void testRatingList() {
		List<User> users = new ArrayList<>();
		users.add(new User(1, "usertest@test.com", "Aa123456789.", "userfullnametest", "USER"));
		users.add(new User(2, "usertest@test.com", "Aa123456789.", "userfullnametest", "USER"));
		users.add(new User(3, "usertest@test.com", "Aa123456789.", "userfullnametest", "USER"));
		
		
		given(userRepository.findAll()).willReturn(users);
		
		List<User> userList = userService.getUserList();
		
		assertEquals(users, userList);
	}
	
	@Test
	void testGetById() {
		Integer id = 1;
		User user = new User(1, "usertest@test.com", "Aa123456789.", "userfullnametest", "USER");
		
		given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
		
		User userExcepted = userService.getById(id);
		
		assertThat(userExcepted).isNotNull();
		assertEquals(user, userExcepted);
	}
	
	@Test
	void testDeleteRating() throws Exception {
		Integer id = 1;
		
		userService.deleteUser(id);
		userService.deleteUser(id);
		
		verify(userRepository, times(2)).deleteById(id);
	}
}
