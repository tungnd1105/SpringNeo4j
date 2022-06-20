package com.felixvn.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.GraphProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
@Getter
@Setter
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -2341499379848575831L;

	@GraphId
	protected Long id;

	@GraphProperty(propertyType = Long.class)
	protected Date createdDate;

	@GraphProperty(propertyType = Long.class)
	protected Date lastModified;

	protected boolean deleted;

	@GraphProperty(propertyType = Long.class)
	protected Date deleteTime;

}
