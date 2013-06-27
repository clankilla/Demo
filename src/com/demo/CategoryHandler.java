package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CategoryHandler {
	private boolean found = false;
	ArrayList<Categories> categoryInfo = new ArrayList<>();
	
	public void searchInDepthAndAdd(ResultSet rs,
											Categories categories) throws SQLException{

		if(categories.getList().size() > 0){
			List<Categories> nodeList = categories.getList();
			
			for(int i = 0; i < nodeList.size(); i++){
				if(nodeList.get(i).getCategoryName()
										.equals(rs.getString("category_parent_node"))){
					nodeList.get(i).addList(new Categories(rs.getString("category_name")));
					found = true;
					break;
				}else
					searchInDepthAndAdd(rs, nodeList.get(i));
			}
		}
	}
	
	public void searchAndAdd(ResultSet rs) throws SQLException{
		
		for(int i = 0; i < categoryInfo.size(); i++){
			if(categoryInfo.get(i).getCategoryName().
											equals(rs.getString("category_parent_node"))){
				categoryInfo.get(i).addList(new Categories(rs.getString("category_name")));
				found = true;
				break;
			}else
				searchInDepthAndAdd(rs, categoryInfo.get(i));
		}
	}
	
	public boolean search(ResultSet rs) throws SQLException{
		boolean doExist = false;
		
		for(int i = 0; i < categoryInfo.size(); i++){
			if(categoryInfo.get(i).getCategoryName().
					equals(rs.getString("category_name"))){
				doExist = true;
				break;
			}
		}
		
		return doExist;
	}
	
	public void getCategoryDB() throws SQLException{
		DataSource ds = null;	
			
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/database-category");
			
			if(ds == null)
				throw new SQLException("Error, could not retrieve data source.");
			
			Connection con = ds.getConnection();
			
			if(con == null)
				throw new SQLException("Error, could not establish connection with database.");
			
			PreparedStatement ps = 
					con.prepareStatement("SELECT category_name, category_parent_node, isRoot FROM category_info");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				if(rs.getBoolean("isRoot")){
					if(!search(rs))
						categoryInfo.add(new Categories(rs.getString("category_name")));
				}else{
					searchAndAdd(rs);
					 if(!found){
						 if(rs.getBoolean("isRoot")){
						 	categoryInfo.add(new Categories(rs.getString("category_parent_node")));
						 	categoryInfo.get(categoryInfo.size()-1)
									.addList(new Categories(rs.getString("category_name")));
						 }
					 }else
					 	found = false;
				}
					
			}
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
}
