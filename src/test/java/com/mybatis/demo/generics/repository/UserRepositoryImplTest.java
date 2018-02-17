package com.mybatis.demo.generics.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mybatis.demo.generics.constants.MockTestList;
import com.mybatis.demo.generics.model.User;
import com.mybatis.demo.generics.repository.UserRepositoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryImplTest implements MockTestList {

	@Mock
	private UserRepositoryImpl userRepository;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFindTAllUser() {
		when(userRepository.selectUserAllUsers()).thenReturn(MockTestList.mockEntityUserList);
		assertTrue(userRepository.selectUserAllUsers().size() > 0);
		verify(userRepository, times(1)).selectUserAllUsers();
	}

	@Test
	public void testFindOneUserById() {
		when(userRepository.selectUserById(2)).thenReturn(MockTestList.mockEntityUserList
				.stream().filter(e -> e.getUserId() == 2).findFirst().orElse(null));

		User user = userRepository.selectUserById(2);
		assertTrue(user != null && user.getEyeColor() != null);
		verify(userRepository, times(1)).selectUserById(2);
	}

	@Test
	public void testSaveUser() {

		doNothing().when(userRepository).insert(MockTestList.mockEntityUserList.get(1));
		userRepository.insert(MockTestList.mockEntityUserList.get(1));

		verify(userRepository, times(1)).insert(MockTestList.mockEntityUserList.get(1));
		verify(userRepository, never()).delete(1);
	}

	@Test
	public void testSaveOrUpdate() {
		User user = MockTestList.mockEntityUserList.get(1);
		doNothing().when(userRepository).save(user, user.getUserId());
		userRepository.saveOrUpdateUser(user, user.getUserId());

		verify(userRepository, never()).delete(user.getUserId());
		verify(userRepository, never()).findOne(user.getUserId());
	}

}
