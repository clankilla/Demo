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
	public CategoryHandler catH;
	private String ulStyle = "", liStyle = "";
	public static final String COMPONENT_TYPE = "CategoryTag";
	
	public void doAction(Object function){
		
	}
	
	public void setUlStyle(String ulStyle){
		this.ulStyle = ulStyle;
	}
	
	public String getUlStyle(){
		return ulStyle;
	}
	
	public void setLiStyle(String liStyle){
		this.liStyle = liStyle;
	}
	
	@Override
	public String getFamily() {
		return COMPONENT_TYPE;
	}
	
	public void renderNodes(ResponseWriter writer, Categories categories) throws IOException{
		List<Categories> nodeList = categories.getList();
		
		if(nodeList.size() > 0){
			writer.startElement("ul", this);
			writer.writeAttribute("class", ulStyle, null);
				for(int i = 0; i < nodeList.size(); i++){
					writer.startElement("li", this);
						writer.writeAttribute("class", liStyle, null);
							writer.startElement("a", this);							
								writer.write(nodeList.get(i).getCategoryName());
							writer.endElement("a");
							renderNodes(writer, nodeList.get(i));
					writer.endElement("li");
				}
			writer.endElement("ul");
		}
	}
	
	public void encodeBegin(FacesContext context) throws IOException{
		catH = new CategoryHandler();
		ResponseWriter writer = context.getResponseWriter();
		

		try {
			catH.getCategoryDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.startElement("ul", this);
		writer.writeAttribute("class", ulStyle, null);
		for(int i = 0; i < catH.categoryInfo.size(); i++){
				writer.startElement("li", this);
				writer.writeAttribute("class", liStyle, null);
					writer.startElement("a", this);
					writer.write(catH.categoryInfo.get(i).getCategoryName());
					writer.endElement("a");
					renderNodes(writer, catH.categoryInfo.get(i));
				writer.endElement("li");
		}
		writer.endElement("ul");
	}
}
