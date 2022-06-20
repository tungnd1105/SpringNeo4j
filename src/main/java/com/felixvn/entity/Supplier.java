package com.felixvn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.support.index.IndexType;

import javax.validation.constraints.NotEmpty;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
@Data
@NodeEntity
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("Supplier")
@EqualsAndHashCode(callSuper = true)
public class Supplier extends BaseEntity {

	private static final long serialVersionUID = -3092141865276172191L;

	@NotEmpty(message = "required code supplier")
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "CodeSupplier")
	private String code;

	@NotEmpty(message = "required name supplier")
	private String name;

}
