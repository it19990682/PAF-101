package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
	// Variables for database details
	private static String url = "jdbc:mysql://localhost:3307/electro";
	private static String dbuser = "root";
	private static String dbpass = "root";
	private static Connection con;
	
	// get connection
	public static Connection connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, dbuser, dbpass);
		}
		catch (Exception e) {
			System.out.println("Database connection unsuccess!");
		}
		
		return con;
	}
}
