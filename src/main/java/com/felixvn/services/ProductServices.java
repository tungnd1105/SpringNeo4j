package com.felixvn.services;

import com.felixvn.entity.GroupProduct;
import com.felixvn.entity.Product;
import com.felixvn.entity.Supplier;

import java.util.List;
import java.util.Map;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/19/2022, Sunday
 **/
public interface ProductServices extends GenericServices<Product> {

	GroupProductServices groupProductServices();

	List<Product> findAll();

	void save(Product groupProduct, Long groupProductId);

	void update(Product product, Long groupProductId);

	void delete(Long id);

	Product findOneById(Long id);

	Map<String,String> fetch(GroupProduct product, Supplier supplier);

}
