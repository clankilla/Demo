package com.demo;

import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;

public class Accesor {
	public static Object getBean(final String beanName){
		Object bean;
		FacesContext fC = FacesContext.getCurrentInstance();
		
		try{
			ELContext context = fC.getELContext();
			bean = context.getELResolver().getValue(context, null, beanName);
		}catch(RuntimeException e){
			throw new FacesException(e.getMessage(), e);
		}
		
		if(bean == null){
			throw new FacesException("Managedbean: "+beanName+" could not be found. " +
					"Please refer to your faces-config.xml for more information.");
		}
		
		return bean;
	}
}
