package beans;

import java.io.Serializable;

public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private int productID;
	private int productAmount;
	private double productPrice;
	
	public OrderDetails() {
		this.id = -1;
		this.productID = -1;
		this.productAmount = 0;
		this.productPrice = 0.0;
	}

	public OrderDetails(int id, int productID, int productAmount, double productPrice) {
		super();
		this.id = id;
		this.productID = productID;
		this.productAmount = productAmount;
		this.productPrice = productPrice;
	}
	
	public OrderDetails(int productID, double productPrice) {
		this.productID = productID;
		this.productPrice = productPrice;
	}
	
	// for test
	public OrderDetails(int productID, int productAmount, double productPrice) {
		this.productID = productID;
		this.productAmount = productAmount;
		this.productPrice = productPrice;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", productID=" + productID + ", productAmount=" + productAmount
				+ ", productPrice=" + productPrice + "]";
	}
	
	

}
