package com.felixvn.common;

import org.primefaces.PrimeFaces;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/19/2022, Sunday
 **/
public class PrimefacesContext {

	public static void onHideDialog(String idDialog) {
		PrimeFaces.current().executeScript("PF('" + idDialog + "').hide()");
	}

}
