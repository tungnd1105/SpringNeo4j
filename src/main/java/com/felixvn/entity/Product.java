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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
@Data
@NodeEntity
@AllArgsConstructor
@NoArgsConstructor
@TypeAlias("Product")
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

	private static final long serialVersionUID = -5759211382089719411L;


	@NotEmpty(message = "required code product")
	@Indexed(unique = true, indexType = IndexType.FULLTEXT, indexName = "CodeProduct")
	private String code;

	@NotEmpty(message = "required name product")
	@Indexed(indexType = IndexType.FULLTEXT, indexName = "NameProduct")
	private String name;

	@Min(value = 0)
	@NotEmpty(message = "required prices product")
	@Pattern(regexp = "[0-9]+", message = "prices product only input number")
	private Double prices;

	@NotEmpty(message = "required currency ")
	private String currency;

	@NotEmpty(message = "required product unit ")
	private String unit;

	@RelatedTo(type = "Product_Group")
	@NotEmpty(message = "required group product ")
	private GroupProduct productGroup;

	@RelatedTo(type = "Product_Supplier")
	@NotEmpty(message = "required supplier product ")
	private Supplier supplier;

}
