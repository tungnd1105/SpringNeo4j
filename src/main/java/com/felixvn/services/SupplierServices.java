package com.felixvn.services;

import com.felixvn.entity.Supplier;

import java.util.List;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
public interface SupplierServices extends GenericServices<Supplier> {
	List<Supplier> findAll();

	Supplier findOneById(Long id);

	Boolean exists(String code);
}
