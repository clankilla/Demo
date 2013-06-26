package com.demo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.faces.bean.*;

public class login implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{user}")
	private user user_info;
	
	public String userExist() throws SQLException{
		DataSource ds = null;
		
		System.out.println(": "+user_info);
		
		try{
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/database-connection");
			
			if(ds == null)
				throw new SQLException("Error, could not retrieve data source.");
			
			Connection con = ds.getConnection();
			
			if(con == null)
				throw new SQLException("Error, could not establish connection with database.");
			
			PreparedStatement ps = 
					con.prepareStatement("SELECT id, username, password FROM login_information");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				/*if(user_info.getName().contentEquals(rs.getString("username")) &&
												user_info.getPassword().contentEquals(rs.getString("password"))){
					return ("welcome");
				}*/
			}
		}catch(NamingException e){
			e.printStackTrace();
		}
		
		
		
		return ("error-login");
	}
	
	
}
