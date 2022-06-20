package com.felixvn.repository;

import com.felixvn.entity.GroupProduct;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
public interface GroupProductRepository extends GraphRepository<GroupProduct> {

	@Query("MATCH (g:GroupProduct) WHERE g.deleted = false RETURN g")
	List<GroupProduct> findAllGroupProduct();

	@Query("START g=node({0}) WHERE g.deleted = false RETURN g")
	GroupProduct findGroupProductById(Long id);

	@Query("START g=node:ProductGroupCode(code={0}) WHERE g.deleted = false WITH COUNT(g) as c RETURN c > 0")
	Boolean existsGroupProductByCode(String code);

	@Query("START g=node({0}) MATCH (g)<-[r]-(p) WHERE p.deleted = false WITH COUNT(p) as c RETURN c > 0")
	Boolean existsRelation(Long id);
}
