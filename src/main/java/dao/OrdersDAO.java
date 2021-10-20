package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Order;

public class OrdersDAO {
	private ConnectionWorker cw;
	private Order order;

	public OrdersDAO(ConnectionWorker cw, Order order) {
		super();
		this.cw = cw;
		this.order = order;
	}

	public  void writeToDB() {
		String identityInsertON = "SET IDENTITY_INSERT orders ON";
		String sql = identityInsertON + " " + "insert into Orders ([user_mail],[order_id],[order_status],"
				+ "[order_date],[order_discount_code],[order_address])" + " values (?, ?, ?, ?, ?, ?)";
		
		Connection conn = cw.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, order.getMail());
			stmt.setInt(2, order.getId());
			stmt.setInt(3, order.getStatus());
			stmt.setDate(4, java.sql.Date.valueOf(order.getDate()));
			stmt.setString(5, order.getDiscountCode());
			stmt.setString(6, order.getAddress());
			
			stmt.execute();
			
			System.out.println("New record added");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
