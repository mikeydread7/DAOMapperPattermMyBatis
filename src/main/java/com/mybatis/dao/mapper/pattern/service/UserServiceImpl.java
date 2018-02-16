package com.mybatis.dao.mapper.pattern.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mybatis.dao.mapper.pattern.generics.dao.GenericMapper;
import com.mybatis.dao.mapper.pattern.generics.service.GenericServiceImpl;
import com.mybatis.dao.mapper.pattern.model.User;
import com.mybatis.dao.mapper.pattern.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserServiceImpl extends GenericServiceImpl<User, Integer>
		implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(
			@Qualifier("userRepositoryImpl") GenericMapper<User, Integer> userRepository) {
		super();
		this.userRepository = (UserRepository) userRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.service.IUserservice#selectUserById(int)
	 */
	@Override
	public User selectUserById(int userId) {

		return userRepository.selectUserById(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mybatis.demo.service.IUserservice#saveUser(com.mybatis.demo.model.
	 * User)
	 */
	@Override
	public void saveUser(User user) {
		userRepository.insertUser(user);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.service.IUserservice#deleteUser(int)
	 */
	@Override
	public void deleteUser(int userId) {
		userRepository.deleteUser(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mybatis.demo.service.IUserservice#updateUser(com.mybatis.demo.model.
	 * User, int)
	 */
	@Override
	public void updateUser(User user, int id) {
		userRepository.saveOrUpdateUser(user, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mybatis.demo.service.IUserservice#selectAllUsers()
	 */
	@Override
	public List<User> selectAllUsers() {

		return userRepository.selectUserAllUsers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mybatis.demo.service.IUserservice#exists(com.mybatis.demo.model.User)
	 */
	@Override
	public Boolean exists(User user) {
		return (null == userRepository.selectUserById(user.getUserId()));

	}
}
