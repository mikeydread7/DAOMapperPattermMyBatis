package com.mybatis.demo.generics.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mybatis.demo.generics.constants.MockTestList;
import com.mybatis.demo.generics.model.User;
import com.mybatis.demo.generics.repository.UserRepository;
import com.mybatis.demo.generics.service.UserService;
import com.mybatis.demo.generics.service.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceIntegrationTest implements MockTestList {

	@Mock
	private UserRepository userRepository;
	@Mock
	private UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl(userRepository);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAll() {
		when(userService.selectAllUsers()).thenReturn(mockEntityUserList);
		assertTrue(userService.selectAllUsers().size() > 1);
	}

	@Test
	public void testById() {
		int userId = 2;
		when(userService.selectUserById(userId)).thenReturn(mockEntityUserList.stream()
				.filter(e -> e.getUserId().intValue() == userId).findFirst()
				.orElse(null));
		assertTrue(userService.selectUserById(userId).getEyeColor().equals("brown"));
	}

	@Test
	public void testSaveOrUpdate() {

		User user = new User(1, "FOO", "brown", 3, 120, new Date(100L), 3);
		userRepository.saveOrUpdateUser(user, 3);
		verify(userRepository, never()).deleteUser(1);

	}

	@Test
	public void testDelete() {

		userRepository.deleteUser(1);
		verify(userRepository, never()).deleteUser(4);
		verify(userRepository, never()).selectUserById(4);
	}

	@Test
	public void testFindById() {
		int userId = 3;
		when(userRepository.selectUserById(userId)).thenReturn(mockEntityUserList.stream()
				.filter(e -> e.getUserId().intValue() == userId).findFirst()
				.orElse(null));
		assertTrue(userService.selectUserById(userId).getUserName().equals("FOOBAR"));

	}

}