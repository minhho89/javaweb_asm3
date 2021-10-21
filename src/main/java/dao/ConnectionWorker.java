package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Account;
import utils.DatabaseUtil;

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
	
	public static ConnectionWorker connectMSSQL() {
		return new ConnectionWorker(DatabaseUtil.SQLSERVER_URL, DatabaseUtil.SQLSERVER_USERNAME,
				DatabaseUtil.SQLSERVER_PASSWORD, DatabaseUtil.SQLSERVER_DRIVE);
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

	@SuppressWarnings("finally")
	public int getMaxOderId() {
		String sql = "SELECT MAX(order_id) AS maxOrder FROM Orders";

		Connection conn = getConnection();

		PreparedStatement stmt;
		int result = 0;
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("maxOrder");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return result;
		}
	}

	@SuppressWarnings("finally")
	public Account getAccountInfo(String user_mail) {
		String sql = "SELECT * FROM account WHERE user_mail=?";

		Account acc = new Account();
		Connection conn = getConnection();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, user_mail);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String password = rs.getString("password");
				int role = rs.getInt("account_role");
				String name = rs.getString("user_name");
				String address = rs.getString("user_address");
				String phone = rs.getString("user_phone");
				// set to acc
				acc.setMail(user_mail);
				acc.setPassword(password);
				acc.setRole(role);
				acc.setName(name);
				acc.setAddress(address);
				acc.setPhone(phone);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return acc;
		}
	}

}
