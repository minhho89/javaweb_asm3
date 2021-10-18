package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Account;

public class ConnectionWorker {

	private String dbUrl;
	private String user;
	private String password;
	private String driverName;

	public ConnectionWorker(String dbUrl, String user, String password, String driverName) {
		this.dbUrl = dbUrl;
		this.user = user;
		this.password = password;
		this.driverName = driverName;
	}

	@SuppressWarnings("finally")
	public Connection getConnection() {
		
		Connection conn = null;
		try {
			// Register Driver
			Class.forName(driverName).getConstructor().newInstance();		
			// Get connection
			conn = DriverManager.getConnection(dbUrl, user, password);
			
			System.out.println("Database connected. User = " + user);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			return conn;
		}
	}
	
	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
				System.out.println("Database connection closed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean doLogin(Account acc) throws SQLException {
		String sql = "SELECT COUNT(*) AS count FROM Account WHERE user_mail=? and password=?";

		Connection conn = getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, acc.getMail());
		stmt.setString(2, acc.getPassword());

		ResultSet rs = stmt.executeQuery();

		int count = 0;

		if (rs.next()) {
			count = rs.getInt("count");
		}
		
		rs.close();

		if (count == 0) {
			return false;
		} else {
			return true;
		}
		
	}

}
