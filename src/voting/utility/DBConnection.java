package voting.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DBConnection {
	 private static Connection conn;
	    
	    static {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            conn = DriverManager.getConnection("jdbc:mysql://localhost/voting?useSSL=false&allowPublicKeyRetrieval=true", "root", "rajsahu08");
	            System.out.println("Connection opened");
	        } catch (ClassNotFoundException ex) {
	            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
	            ex.printStackTrace();
	        } catch (SQLException se) {
	            se.printStackTrace();
	        }
	    }
	    public static Connection getConnection() {
	        return conn;
	    }
	    
	    public static void closeConnection() {
	        try { 
	            conn.close();
	            System.out.println("Connection Closed");
	        } catch (SQLException ex) {
	            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
	            ex.printStackTrace();
	        }
	    }
}
