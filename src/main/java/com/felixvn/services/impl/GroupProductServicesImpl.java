package com.felixvn.services.impl;

import com.felixvn.common.NotifyContext;
import com.felixvn.common.PrimefacesContext;
import com.felixvn.entity.GroupProduct;
import com.felixvn.entity.Supplier;
import com.felixvn.repository.GroupProductRepository;
import com.felixvn.services.GroupProductServices;
import com.felixvn.services.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
@Service
public class GroupProductServicesImpl extends GenericServicesImpl<GroupProduct> implements GroupProductServices {

	@Autowired
	public Neo4jTemplate neo4jTemplate;

	@Autowired
	@Qualifier("supplierServicesImpl")
	public SupplierServices supplierServices;

	@Autowired
	public GroupProductRepository groupProductRepository;

	public Map<Long, Supplier> mapSupplier = new HashMap<>();

	@Override
	public SupplierServices supplierServices() {
		return supplierServices;
	}

	@Override
	public List<GroupProduct> findAll() {
		return groupProductRepository.findAllGroupProduct();
	}

	@Override
	public void save(GroupProduct groupProduct, Long supplierId) {
		Boolean bool = groupProductRepository.existsGroupProductByCode(groupProduct.getCode());
		Supplier supplier = supplierServices.findOneById(supplierId);
		if (!bool && supplier != null) {
			setDateTime(groupProduct);
			groupProduct.setSupplier(supplier);
			groupProductRepository.save(groupProduct);
			NotifyContext.groupProductSave();
			NotifyContext.ajaxUpdateMessages();
			PrimefacesContext.onHideDialog("dialogGroupProduct");
		}
	}

	@Override
	public void update(GroupProduct groupProduct, Long supplierId) {
		Supplier supplier = supplierServices.findOneById(supplierId);
		GroupProduct groupProductOld = groupProductRepository.findOne(groupProduct.getId());
		if (supplier != null && groupProductOld != null) {
			setDateTime(groupProduct);
			groupProductOld.setName(groupProduct.getName());
			groupProduct.setSupplier(supplier);
			groupProductRepository.save(groupProductOld);
			NotifyContext.groupProductSave();
			NotifyContext.ajaxUpdateMessages();
			PrimefacesContext.onHideDialog("dialogGroupProduct");
		}
	}

	@Override
	public void delete(Long id) {
		Boolean bool = groupProductRepository.existsRelation(id);
		if (!bool) {
			groupProductRepository.delete(id);
		}
		NotifyContext.ajaxUpdateMessages();
		NotifyContext.groupProductDeleted(bool);
	}

	@Override
	public GroupProduct findOneById(Long id) {
		return groupProductRepository.findGroupProductById(id);
	}

	@Override
	public Map<String, String> fetch(Supplier supplier) {
		Map<String, String> mapSupplierProperties = new HashMap<>();
		if (supplier != null) {
			mapSupplier.putIfAbsent(supplier.getId(), neo4jTemplate.fetch(supplier));
			mapSupplierProperties.put("supplierName", mapSupplier.get(supplier.getId()).getName());
		}
		return mapSupplierProperties;
	}

}
