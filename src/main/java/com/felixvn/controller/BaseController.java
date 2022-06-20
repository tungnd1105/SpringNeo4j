package com.felixvn.controller;

import com.felixvn.common.SpringJSFUtil;

import java.io.Serializable;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/18/2022, Saturday
 **/
public class BaseController implements Serializable {

	private static final long serialVersionUID = -6014040800001899156L;

	protected Object getBean(String beanName) {
		return SpringJSFUtil.getBean(beanName);
	}
}
