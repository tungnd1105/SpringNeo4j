package com.felixvn.services;

import java.util.List;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
public interface GenericServices<T> {
	void setDateTime(T entity);

	T save(T entity);

	Iterable<T> save(Iterable<T> entity);

	List<T> findAll();

	T findOneById(Long id);

	void delete(Long id);

	<H> H fetch(H value);
}
