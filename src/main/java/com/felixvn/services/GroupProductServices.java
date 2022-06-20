package com.felixvn.services;

import com.felixvn.entity.GroupProduct;
import com.felixvn.entity.Supplier;
import com.felixvn.services.impl.SupplierServicesImpl;

import java.util.List;
import java.util.Map;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
public interface GroupProductServices extends GenericServices<GroupProduct> {
	List<GroupProduct> findAll();

	void save(GroupProduct groupProduct, Long supplierId);

	void update(GroupProduct groupProduct, Long supplierId);

	void delete(Long id);

	GroupProduct findOneById(Long id);

	Map<String,String> fetch(Supplier supplier);
	SupplierServices supplierServices ();
}
