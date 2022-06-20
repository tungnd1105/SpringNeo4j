package com.felixvn.repository;

import com.felixvn.entity.Supplier;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
@Repository
public interface SupplierRepository extends GraphRepository<Supplier> {

	@Query("START s=node({0}) WHERE s.deleted = false RETURN s")
	Supplier findSuppliersById(Long id);

	@Query("START s=node:CodeSupplier(code={0}) WHERE s.deleted = false WITH COUNT(s) as c RETURN c > 0")
	Boolean existsSupplierByCode(String code);

	@Query("MATCH (s:Supplier) WHERE s.deleted = false RETURN s")
	List<Supplier> findAllSuppliers();
}
