package gestionBilicence.general.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.postgresql.util.PSQLException;

public class MySQLFactory extends AbstractDaoFactory {
	/*
	 * Create a connection to a MySQL database
	 */
	private static Connection conn;

	public MySQLFactory(String[] infoConn) {
		/*
		try {
	        Class.forName("org.postgresql.Driver");
	        String url = "jdbc:postgresql://"+infoConn[2]+"/"+infoConn[3];
	        String user = infoConn[0];
	        String passwd = infoConn[1];
	        conn = DriverManager.getConnection(url, user, passwd);
	        // commits automatiques ou pas
	        conn.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
	        e.printStackTrace();
		} catch (PSQLException e){
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null , "Wrong password?", "PostGreSQLFactory -- PSQLException", JOptionPane.ERROR_MESSAGE );
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		*/
		System.out.println("MySQLFactory ready!");
	}

	@Override
	public Dao getStudentDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao getExamsDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dao getDao(int i) {
		// Idea: make a switch over all possible entities
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractSemesterDao getSemesterDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
