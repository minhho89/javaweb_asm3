package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Product;
import utils.OrderDetailsList;

public class ProductDAO {

	private ConnectionWorker cw;
	
	
	public ProductDAO(ConnectionWorker cw) {
		super();
		this.cw = cw;
	}
	
	
	public ArrayList<Product> searchProductByName(String name) throws SQLException {
		ArrayList<Product> products = new ArrayList<>();
		
		String sql = "SELECT *  FROM Products WHERE product_name like '%" + name  + "%'";
		
		Connection conn = cw.getConnection();

		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			int id = rs.getInt("product_id");		
			String productName = rs.getString("product_name");
			String des = rs.getString("product_des");
			Float price = rs.getFloat("product_price");
			String imgSource = rs.getString("product_img_source");
			String type = rs.getString("product_type");
			String brand = rs.getString("product_brand");
			
			Product p = new Product(id, productName, des, price, imgSource, type, brand);
			products.add(p);
			
			System.out.println(p);
		}
		
		
		
		return products;
	}
	
	
}
