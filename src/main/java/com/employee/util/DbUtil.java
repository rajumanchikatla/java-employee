package com.employee.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	
	Connection conn = null;

	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/my_first_schema?characterEncoding=utf8","root","root"); 
		} catch (ClassNotFoundException | SQLException e)
		
		{
			e.printStackTrace();
		}  
		return conn;
	}
	
	public void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}