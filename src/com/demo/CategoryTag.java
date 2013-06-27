package com.demo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;


@FacesComponent("CategoryTag")
public class CategoryTag  extends UIComponentBase{
	private String value;
	public static final String COMPONENT_TYPE = "CategoryTag";
	
	@Override
	public String getFamily() {
		return COMPONENT_TYPE;
	}
	
	public void encodeBegin(FacesContext context) throws IOException{
		CategoryHandler catH = new CategoryHandler();
		ResponseWriter writer = context.getResponseWriter();
		
		try {
			catH.getCategoryDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.startElement("ul", this);
		for(int i = 0; i < catH.categoryInfo.size(); i++){
			writer.startElement("li", this);
			writer.write(catH.categoryInfo.get(i).getCategoryName());
			writer.endElement("li");
			
			List<Categories> subcategory = catH.categoryInfo.get(i).getList();
			if(subcategory.size() > 0){
				writer.startElement("li", this);
				writer.startElement("ul", this);
				for(int j = 0; j < subcategory.size(); j++){
					writer.startElement("li", this);
					writer.write(subcategory.get(j).getCategoryName());
					writer.endElement("li");
				}
				writer.endElement("ul");
				writer.endElement("li");
			}
		}
		writer.endElement("ul");
	}
}
