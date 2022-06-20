package com.felixvn.services.impl;

import com.felixvn.common.NotifyContext;
import com.felixvn.common.PrimefacesContext;
import com.felixvn.entity.GroupProduct;
import com.felixvn.entity.Product;
import com.felixvn.entity.Supplier;
import com.felixvn.repository.ProductRepository;
import com.felixvn.services.GroupProductServices;
import com.felixvn.services.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/19/2022, Sunday
 **/
@Service
public class ProductServicesImpl extends GenericServicesImpl<Product> implements ProductServices {

	@Autowired
	public Neo4jTemplate neo4jTemplate;

	@Autowired
	public ProductRepository productRepository;

	@Autowired
	public GroupProductServices groupProductServices;

	public Map<Long, GroupProduct> mapGroupProduct = new HashMap<>();
	public Map<Long, Supplier> mapSupplier = new HashMap<>();

	@Override
	public GroupProductServices groupProductServices() {
		return groupProductServices;
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAllProduct();
	}

	@Override
	public void save(Product product, Long groupProductId) {
		GroupProduct groupProduct = groupProductServices.findOneById(groupProductId);
		Boolean bool = productRepository.existsProductById(product.getCode());
		if (!bool && groupProduct != null) {
			setDateTime(product);
			product.setProductGroup(groupProduct);
			product.setSupplier(groupProduct.getSupplier());
			productRepository.save(product);
			NotifyContext.productSave();
			NotifyContext.ajaxUpdateMessages();
			PrimefacesContext.onHideDialog("dialogProduct");
		}
	}

	@Override
	public void update(Product product, Long groupProductId) {
		Product productOld = productRepository.findProductById(product.getId());
		GroupProduct groupProduct = groupProductServices.findOneById(groupProductId);
		if (productOld != null && groupProduct != null) {
			setDateTime(product);
			product.setProductGroup(groupProduct);
			product.setSupplier(groupProduct.getSupplier());
			productRepository.save(product);
			NotifyContext.productSave();
			NotifyContext.ajaxUpdateMessages();
			PrimefacesContext.onHideDialog("dialogProduct");
		}
	}

	@Override
	public Product findOneById(Long id) {
		return productRepository.findProductById(id);
	}

	@Override
	public Map<String, String> fetch(GroupProduct product, Supplier supplier) {
		Map<String, String> mapEntity = new HashMap<>();
		if (product != null && supplier != null) {
			mapGroupProduct.putIfAbsent(product.getId(), neo4jTemplate.fetch(product));
			mapSupplier.putIfAbsent(supplier.getId(), neo4jTemplate.fetch(supplier));
			mapEntity.putIfAbsent("groupName", mapGroupProduct.get(product.getId()).getName());
			mapEntity.putIfAbsent("supplierName", mapSupplier.get(supplier.getId()).getName());
		}

		return mapEntity;
	}

	@Override
	public void delete(Long id) {
		productRepository.delete(id);
		NotifyContext.productDeleted();
		NotifyContext.ajaxUpdateMessages();
	}
}
