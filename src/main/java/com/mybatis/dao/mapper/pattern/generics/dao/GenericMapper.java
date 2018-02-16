package com.mybatis.dao.mapper.pattern.generics.dao;

import java.util.List;

public interface GenericMapper<E, K> {
	E findOne(K key);

	List<E> findAll();

	void save(E entity, K key);

	void insert(E entity);

	void delete(K key);

}