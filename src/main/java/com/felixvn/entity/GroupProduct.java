package com.felixvn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
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
@TypeAlias("GroupProduct")
@EqualsAndHashCode(callSuper = true)
public class GroupProduct extends BaseEntity{

	private static final long serialVersionUID = -8714998554849415337L;

	@NotEmpty(message = "require product group code")
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "ProductGroupCode")
	private String code;

	@NotEmpty(message = "require name group product")
	private String name;

	@NotEmpty(message = "require product group supplier")
	@RelatedTo(type = "Product_Group_Supplier")
	private Supplier supplier;

}
