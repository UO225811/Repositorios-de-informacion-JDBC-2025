package uo.ri.cws.application.ui.manager.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import uo.ri.util.console.Console;
import uo.ri.util.exception.BusinessException;
import uo.ri.util.menu.Action;

public class AddMechanicAction implements Action {

	private static String SQL = "insert into TMechanics(id, nif, name, surname, version) values (?, ?, ?, ?, ?)";
	private static final String URL = "jdbc:hsqldb:hsql://localhost";
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	@Override
	public void execute() throws BusinessException {
		
		// Get info
		String nif = Console.readString("nif"); 
		String name = Console.readString("Name"); 
		String surname = Console.readString("Surname");
		
		// Process
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, UUID.randomUUID().toString());
			pst.setString(2, nif);
			pst.setString(3, name);
			pst.setString(4, surname);
			pst.setLong(5, 1L);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (rs != null) try { rs.close(); } catch(SQLException e) { /* ignore */ }
			if (pst != null) try { pst.close(); } catch(SQLException e) { /* ignore */ }
			if (c != null) try { c.close(); } catch(SQLException e) { /* ignore */ }
		}
		
		// Print result
		Console.println("Mechanic added");
	}

}
