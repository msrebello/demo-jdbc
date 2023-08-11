package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			// INITIALIZES CONNECTION AND STATEMENT
			// EXAMPLE 1:
			
			connection = DB.getConnection();

			ps = connection.prepareStatement("INSERT INTO seller "
											+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) " 
											+ "VALUES " + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			
			ps.setString(1, "Joao");
			ps.setString(2, "joao@gmail.com");
			ps.setDate(3, Date.valueOf(LocalDate.parse("04/08/2000", format)));
			ps.setDouble(4, 1800.00);
			ps.setInt(5, 3);
			
			// EXAMPLE 2:
						//st = conn.prepareStatement(
						//		"INSERT INTO department (Name) values ('D1'),('D2')", 
						//		Statement.RETURN_GENERATED_KEYS);

			// THROWS A QUERY INTO DB AND SAVES THE NUMBERS OF ROWS AFFECTED IN QUERY

			int rows_affected = ps.executeUpdate();
			
			// SHOW THE NEW INSERTED ID's

			if (rows_affected > 0) {
				rs = ps.getGeneratedKeys();

				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("UPDATED! ID: " + id);
				}
			}
			
			else
				System.out.println("No rows affected!");
		
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			DB.closeResulSet(rs);
			DB.closeStatement(ps);
			DB.closeConnection();

		}
	}

}
