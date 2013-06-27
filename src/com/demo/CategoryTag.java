package com.demo;

import java.io.IOException;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


@FacesComponent("CategoryTag")
public class CategoryTag  extends UIComponentBase{
	private String value;
	public static final String COMPONENT_TYPE = "CategoryTag";

	public String getValue(){
		return value;
	}
	
	@Override
	public String getFamily() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void encodeBegin(FacesContext context) throws IOException{
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("Tree", this);
		writer.write(value);
		writer.endElement("Tree");
	}
	
	public void setValue(String value){
		this.value = value;
	}

}
