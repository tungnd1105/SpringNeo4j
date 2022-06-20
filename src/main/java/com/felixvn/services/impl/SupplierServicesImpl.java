package com.felixvn.services.impl;

import com.felixvn.entity.Supplier;
import com.felixvn.repository.SupplierRepository;
import com.felixvn.services.SupplierServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
@Component
public class SupplierServicesImpl extends GenericServicesImpl<Supplier> implements SupplierServices {


	private static final long serialVersionUID = -5971773095721131913L;

	@Autowired
	public SupplierRepository supplierRepository;

	public void init() {
		List<Supplier> supplierList = new ArrayList<>();
		Supplier amd = new Supplier("amd","AMD");
		Supplier intel = new Supplier("intel","Intel");
		Supplier nvidia = new Supplier("nvidia","Nvidia");
		Supplier razer = new Supplier("razer","Razer");
		Supplier samsung = new Supplier("samsung","Samsung");
		Supplier asus = new Supplier("asus","Asus");
		Supplier lenovo = new Supplier("lenovo","Lenovo");
		Supplier hp = new Supplier("hp","Hp");
		Supplier dell = new Supplier("dell","Dell");
		Supplier apple = new Supplier("apple","Apple");

		supplierList.add(amd);
		supplierList.add(intel);
		supplierList.add(nvidia);
		supplierList.add(razer);
		supplierList.add(samsung);
		supplierList.add(asus);
		supplierList.add(lenovo);
		supplierList.add(hp);
		supplierList.add(dell);
		supplierList.add(apple);
		supplierRepository.save(supplierList);
	}

	@Override
	public Supplier findOneById( Long id) {
		return supplierRepository.findSuppliersById(id);
	}

	@Override

	public List<Supplier> findAll() {
		return supplierRepository.findAllSuppliers();
	}

	@Override
	public Boolean exists( String code) {
		return supplierRepository.existsSupplierByCode(code);
	}
}
