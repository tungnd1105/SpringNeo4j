package com.felixvn.controller;

import com.felixvn.entity.GroupProduct;
import com.felixvn.entity.Product;
import com.felixvn.entity.Supplier;
import com.felixvn.services.ProductServices;
import com.felixvn.services.impl.ProductServicesImpl;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.Map;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/19/2022, Sunday
 **/
@ManagedBean
@ViewScoped
@Getter
@Setter
public class ProductController extends BaseController {

	private Long groupId;
	private String supplierName;
	private Product product;
	private GroupProduct groupProduct;
	private ProductServices productServices;


	public ProductController() {
		this.groupId = null;
		this.supplierName = null;
		this.groupProduct = new GroupProduct();
		this.product = new Product();
		this.productServices = (ProductServices) getBean("productServicesImpl");
	}

	public List<Product> findAll() {
		return productServices.findAll();
	}

	public void save() {
		if (product.getId() != null) {
			productServices.update(product, groupId);
		} else {
			productServices.save(product, groupId);
		}
	}

	public void onSelectGroupProduct() {
		this.groupProduct = productServices.groupProductServices().findOneById(groupId);
		this.supplierName = groupProduct.getSupplier().getName();
	}

	public void findOne(Long id) {
		this.product = productServices.findOneById(id);
		this.groupProduct = product.getProductGroup();
		this.supplierName = groupProduct.getSupplier().getName();
	}

	public Map<String, String> fetch(GroupProduct groupProduct, Supplier supplier) {
		return productServices.fetch(groupProduct,supplier);
	}

	public void delete(Long id) {
		productServices.delete(id);
	}

}
