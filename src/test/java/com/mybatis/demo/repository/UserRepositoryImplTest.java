package com.mybatis.demo.repository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.mybatis.dao.mapper.pattern.mapper.UserMapper;
import com.mybatis.dao.mapper.pattern.model.User;
import com.mybatis.dao.mapper.pattern.repository.UserRepositoryImpl;
import com.mybatis.demo.constants.MockTestList;

@RunWith(MockitoJUnitRunner.class)
public class UserRepositoryImplTest implements MockTestList {

	// @Resource
	@Mock
	private UserMapper userMapperMock;

	@InjectMocks
	private UserRepositoryImpl userRepository;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindTAllUser() {
		when(userMapperMock.findAll()).thenReturn(MockTestList.mockEntityUserList);
		assertTrue(userRepository.selectUserAllUsers().size() > 0);
		verify(userMapperMock, times(1)).findAll();
	}

	@Test
	public void testFindOneUserById() {
		when(userMapperMock.findOne(2)).thenReturn(MockTestList.mockEntityUserList
				.stream().filter(e -> e.getUserId() == 2).findFirst().orElse(null));

		User user = userRepository.selectUserById(2);
		assertTrue(user != null && user.getEyeColor() != null);
		verify(userMapperMock, times(1)).findOne(2);
	}

	@Test
	public void testSaveUser() {

		doNothing().when(userMapperMock).insert(MockTestList.mockEntityUserList.get(1));
		userRepository.insertUser(MockTestList.mockEntityUserList.get(1));

		verify(userMapperMock, times(1)).insert(MockTestList.mockEntityUserList.get(1));
		verify(userMapperMock, never()).delete(1);
	}

	@Test
	public void testSaveOrUpdate() {
		User user = MockTestList.mockEntityUserList.get(1);
		doNothing().when(userMapperMock).save(user, user.getUserId());
		userRepository.saveOrUpdateUser(user, user.getUserId());

		verify(userMapperMock, never()).delete(user.getUserId());
		verify(userMapperMock, never()).findOne(user.getUserId());
	}

}
