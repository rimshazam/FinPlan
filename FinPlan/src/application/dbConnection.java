package application;
import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
	
	public static Connection connectDB() {
		
		Connection con;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/finplandatabase?serverTimezone=UTC", "root", "rymes4");
			System.out.println("Connection to MySQL established.");
			
			return con;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
