package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.OrderDetails;
import utils.OrderDetailsList;

public class OrderDetailsDAO {
	
	private ConnectionWorker cw;
	private OrderDetailsList odlist;

	public OrderDetailsDAO(ConnectionWorker cw, OrderDetailsList odlist) {
		this.cw = cw;
		this.odlist = odlist;
	}
	
	public void writeToDatabase() {
		ArrayList<OrderDetails> list = odlist.getList();
		
		for (OrderDetails od : list) {
			String identityInsertON = "SET IDENTITY_INSERT orders ON";
			String sql = identityInsertON + " " + "insert into Orders_detail (order_id, product_id, amount_product, price_product)" + " values (?, ?, ?, ?)";
			
			Connection conn = cw.getConnection();
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, od.getId());
				stmt.setInt(2, od.getProductID());
				stmt.setInt(3, od.getProductAmount());
				stmt.setDouble(4, od.getProductPrice());
				
				stmt.execute();

				System.out.println("New order details added: " + od.toString());

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
	}

}
