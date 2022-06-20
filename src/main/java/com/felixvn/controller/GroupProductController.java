package com.felixvn.controller;

import com.felixvn.entity.GroupProduct;
import com.felixvn.entity.Supplier;
import com.felixvn.services.GroupProductServices;
import com.felixvn.services.impl.GroupProductServicesImpl;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.Map;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
@ManagedBean
@ViewScoped
@Getter
@Setter
public class GroupProductController extends BaseController {

	private static final long serialVersionUID = -533757408884665125L;

	private Long supplierId;
	private Supplier supplier;
	private GroupProduct groupProduct;
	private GroupProductServices groupProductServices;

	public GroupProductController() {
		this.supplierId = null;
		this.supplier = new Supplier();
		this.groupProduct = new GroupProduct();
		this.groupProductServices = (GroupProductServices) getBean("groupProductServicesImpl");
	}

	public List<GroupProduct> findAll() {
		return groupProductServices.findAll();
	}

	public List<Supplier> findAllSupplier() {
		return groupProductServices.supplierServices().findAll();
	}

	public void save() {
		if (groupProduct.getId() != null) {
			groupProductServices.update(groupProduct, supplierId);
		} else {
			groupProductServices.save(groupProduct, supplierId);
		}
	}

	public void findOne(Long id) {
		this.groupProduct = groupProductServices.findOneById(id);
		this.supplier = groupProduct.getSupplier();
	}

	public void delete(Long id) {
		groupProductServices.delete(id);
	}

	public Map<String, String> fetch(Supplier supplier){
		return groupProductServices.fetch(supplier);
	}

}
