package com.felixvn.repository;

import com.felixvn.entity.Product;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import java.util.List;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
public interface ProductRepository extends GraphRepository<Product> {

	@Query("MATCH (p:Product) WHERE p.deleted = false RETURN p")
	List<Product> findAllProduct();

	@Query("START p=node({0}) WHERE p.deleted = false RETURN p")
	Product findProductById(Long id);

	@Query("START n=node:CodeProduct(code={0}) WHERE n.deleted = false WITH COUNT(n) as c RETURN c > 0")
	Boolean existsProductById(String code);





}
