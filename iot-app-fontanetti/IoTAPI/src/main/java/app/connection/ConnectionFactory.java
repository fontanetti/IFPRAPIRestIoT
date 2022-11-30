package app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
private static Connection conn = null;
	
	public static Connection getConnection() {
		
		String database;
		String user;
		String password;
		
		if(conn == null) {
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/iot_lab", "root", "xxxxxx");
			
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
		
		return conn;
		
	}
	
	public static void close() {
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

}
