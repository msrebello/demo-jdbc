package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		// PROPERTIES OF TRANSACTIONS:
		//
		// ATOMICITY: SINGLE OPERATION
		// CONSISTENCY: DATA MUST BE CONSISTENT AT THE BEGINNING AND END OF THE TRANSACTION
		// ISOLATION: INVISIBILITY AND SERIALIZABLE
		// DURABILITY: AFTER TRANSACTION SUCCESSFULLY, CHANGES TO DATA PERSIST AND ARE NOT UNDONE
		
		Connection connection = null;
		Statement st = null;
		
		try {
			
			// INITIALIZES CONNECTION AND STATEMENT
			
			connection = DB.getConnection();
			
			connection.setAutoCommit(false);
			
			st = connection.createStatement();
			
			
			// THROWS A QUERY INTO DB AND SAVES THE NUMBERS OF ROWS AFFECTED IN QUERY
			
			int rows1 = st.executeUpdate("UPDATE department BaseSalary = 2000.00 WHERE DepartmentId = 2");
			int rows2 = st.executeUpdate("UPDATE department BaseSalary = 3000.00 WHERE DepartmentId = 1");
			
			connection.commit();
			
			System.out.println("rows1 = " + rows1);
			System.out.println("rows2 = " + rows2);
			
		} 
		catch (SQLException e) {
			
			// IF THERE IS THROWING OF SQL EXCEPTION THE ROLLBACK RETURNS DOES NOT EXECUTE THE COMMIT
			
			try {
				connection.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			}
			catch (SQLException e1) {
				throw new DbException("Failed rollback! Caused by: " + e.getMessage());
				
			}
		}
		
		finally {
			
			DB.closeStatement(st);
			DB.closeConnection();
			
		}
	}

}
