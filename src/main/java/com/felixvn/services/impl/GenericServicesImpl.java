package com.felixvn.services.impl;

import com.felixvn.entity.BaseEntity;
import com.felixvn.services.GenericServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
@Transactional
public abstract class GenericServicesImpl<T extends BaseEntity> implements GenericServices<T>, Serializable {

	@Autowired
	private GraphRepository<T> repository;

	@Autowired
	private Neo4jTemplate neo4jTemplate;

	@Override
	public void setDateTime(T entity) {
		if (entity.getId() != null){
			entity.setLastModified(new Date());
		}else {
			entity.setCreatedDate(new Date());
		}
	}

	@Override
	@Transactional
	public T save(T entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public Iterable<T> save(Iterable<T> entity) {
		return repository.save(entity);
	}

	@Transactional
	@Override
	public List<T> findAll() {
		List<T> result = new ArrayList<T>();
		Result<T> eResult = repository.findAll();
		for (T t: eResult) {
			result.add(t);
		}
		return result;
	}

	@Override
	@Transactional
	public T findOneById(Long id) {
		return repository.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	@Transactional
	public <H> H fetch(H value) {
		return neo4jTemplate.fetch(value);
	}

}
