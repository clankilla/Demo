package com.demo;

import java.util.ArrayList;
import java.util.List;

public class Categories{
	private String category_name;
	private List<Categories> nodes;
	
	public Categories(String category_name){
		this.category_name = category_name;
		nodes = new ArrayList<>();
	}
	
	public String getCategoryName(){
		return category_name;
	}
	
	public void addList(Categories node){
		nodes.add(node);
	}
	
	public List<Categories> getList(){
		if(nodes != null)
			return nodes;
		return null;
	}
	
}