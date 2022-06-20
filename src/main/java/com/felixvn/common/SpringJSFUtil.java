
package com.felixvn.common;

import javax.faces.context.FacesContext;


public class SpringJSFUtil {

	public static <T> T getBean(String beanName) {
		if (beanName == null) {
			return null;
		}
		return getValue("#{" + beanName + "}");
	}

	private static <T> T getValue(String expression) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context,
			expression, Object.class);
	}

}
