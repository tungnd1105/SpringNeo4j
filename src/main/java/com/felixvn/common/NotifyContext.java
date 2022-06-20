package com.felixvn.common;

import org.primefaces.PrimeFaces;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author : Duc Tung
 * @project : SpringNeo4j
 * @created : 6/19/2022, Sunday
 **/
public class NotifyContext {

	public static void ajaxUpdateMessages() {
		PrimeFaces.current().ajax().update("form:messages");
	}

	public static void groupProductSave() {
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Notify", "Group Product saved"));
	}

	public static void groupProductDeleted(Boolean bool) {
		if (bool) {
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, "Notify", "You must delete all product in group before delete group")
				);
		} else {
			FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_INFO, "Notify", "Grpoup Product deleted")
				);
		}
	}

	public static void productSave() {
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Notify", "Product saved"));
	}

	public static void productDeleted() {
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Notify", "Product deleted"));
	}

}
