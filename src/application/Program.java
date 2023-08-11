package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {
		
		Connection connection = null;
		Statement statement = null;
		ResultSet reSet = null;
		
		try {
			connection = DB.getConnection();
			
			statement = connection.createStatement();
		
			reSet = statement.executeQuery("SELECT * FROM department");
			
			while (reSet.next()) {
				System.out.println(reSet.getInt("Id") + ", " + reSet.getString("Name"));
			}
			
		} catch (Exception e) {
			throw new DbException("### ERROR: " + e.getMessage());
		}
		
		finally {
			DB.closeResulSet(reSet);
			DB.closeStatement(statement);
			DB.closeConnection();
		}
		

	}

}
