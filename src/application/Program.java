package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			
			// INITIALIZES CONNECTION AND STATEMENT	
			
			connection = DB.getConnection();
			ps = connection.prepareStatement("DELETE FROM seller "
											+ "WHERE "
											+ "Id = ?");
			
			ps.setInt(1, 8);
		
			// THROWS A QUERY INTO DB AND SAVES THE NUMBERS OF ROWS AFFECTED IN QUERY
			
			int rows_affected = ps.executeUpdate();
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			
			DB.closeStatement(ps);
			DB.closeConnection();
			
		}
	}

}
