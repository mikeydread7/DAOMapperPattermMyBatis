package com.mybatis.dao.mapper.pattern.repository;

import java.util.List;

import com.mybatis.dao.mapper.pattern.generics.dao.GenericMapper;
import com.mybatis.dao.mapper.pattern.model.User;

public interface UserRepository extends GenericMapper<User, Integer> {

	User selectUserById(int id);

	void saveOrUpdateUser(User user, int userrId);

	void insertUser(User user);

	void deleteUser(int id);

	List<User> selectUserAllUsers();

}