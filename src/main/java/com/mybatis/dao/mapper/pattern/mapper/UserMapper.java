package com.mybatis.dao.mapper.pattern.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.mybatis.dao.mapper.pattern.generics.dao.GenericMapper;
import com.mybatis.dao.mapper.pattern.model.User;

@Mapper
public interface UserMapper extends GenericMapper<User, Integer> {
	static final String SELECT_STR = "userId, userName, eyeColor, height, weight, birthday ,userAge";

	@Select("select " + SELECT_STR + " from USER_PROFILES where userId=#{userId}")
	User findOne(int userId);

	@Select("select " + SELECT_STR + " from USER_PROFILES")
	List<User> findAll();

	@Insert("INSERT into USER_PROFILES VALUES (user_id_seq.nextval, #{User.userName},#{User.eyeColor},"
			+ "#{User.height},#{User.weight},#{User.birthday},#{User.userAge} )")
	void insert(@Param("User") User user);

	@Update("UPDATE USER_PROFILES SET userName=#{User.userName}, eyeColor=#{User.eyeColor}, "
			+ "height=#{User.height}, weight=#{User.weight}, birthday=#{User.birthday}, "
			+ "userAge=#{User.userAge} where userId=#{id}")
	void save(@Param("User") User user, @Param("id") int id);

	@Delete("DELETE FROM USER_PROFILES WHERE userId=#{id}")
	void delete(@Param("id") int id);

	default List<User> defaultFindAll() {
		return findAll();
	}

}
