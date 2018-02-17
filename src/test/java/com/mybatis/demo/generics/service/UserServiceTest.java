package com.mybatis.demo.generics.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mybatis.demo.generics.constants.MockTestList;
import com.mybatis.demo.generics.service.UserServiceImpl;

public class UserServiceTest {

	
	@Mock
	private UserServiceImpl userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAll() {
		when(userService.selectAllUsers())
				.thenReturn(MockTestList.mockEntityUserList);
		assertTrue(userService.selectAllUsers().size() > 1);
		verify(userService, times(1)).selectAllUsers();
	}

	@Test
	public void testById() {
		int userId = 2;
		when(userService.selectUserById(userId))
				.thenReturn(MockTestList.mockEntityUserList.stream()
						.filter(e -> e.getUserId().intValue() == userId).findFirst()
						.orElse(null));
		assertTrue(userService.selectUserById(userId).getEyeColor().equals("brown"));

	}

	@Test
	public void testFindById() {
		int userId = 3;
		when(userService.selectUserById(userId))
				.thenReturn(MockTestList.mockEntityUserList.stream()
						.filter(e -> e.getUserId().intValue() == userId).findFirst()
						.orElse(null));
		assertTrue(userService.selectUserById(userId).getUserName().equals("FOOBAR"));

	}
}
