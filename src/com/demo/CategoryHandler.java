package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class CategoryHandler {
	ArrayList<Categories> categoryInfo = new ArrayList<>();	
	
	public boolean searchAndAdd(ResultSet rs) throws SQLException{
		boolean found = false;
		
		for(int i = 0; i < categoryInfo.size(); i++){
			if(categoryInfo.get(i).getCategoryName().
											equals(rs.getString("category_parent_node"))){
				categoryInfo.get(i).addList(new Categories(rs.getString("category_name")));
				found = true;
				break;
			}
		}
		return found;
	}
	
	public boolean search(ResultSet rs) throws SQLException{
		boolean found = false;
		
		for(int i = 0; i < categoryInfo.size(); i++){
			if(categoryInfo.get(i).getCategoryName().
					equals(rs.getString("category_name"))){
				found = true;
				break;
			}
		}
		
		return found;
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
					con.prepareStatement("SELECT category_name, category_parent_node FROM category_info");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				if(rs.getString("category_parent_node").equals("")){
					if(!search(rs))
						categoryInfo.add(new Categories(rs.getString("category_name")));
				}else{
					 if(!searchAndAdd(rs)){
						categoryInfo.add(new Categories(rs.getString("category_parent_node")));
						categoryInfo.get(categoryInfo.size()-1)
									.addList(new Categories(rs.getString("category_name")));
					 }
				}
					
			}
		}catch(NamingException e){
			e.printStackTrace();
		}
		
	}
}
