package utils;

import java.util.ArrayList;
import java.util.List;

import beans.OrderDetails;

public class OrderDetailsList {
	private ArrayList<OrderDetails> orderDetailsList;
	
	public OrderDetailsList() {
		this.orderDetailsList = new ArrayList<OrderDetails>();
	}
	
	public OrderDetailsList(ArrayList<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}
	
	public OrderDetails getOrderDetailsObjectByProId(int productId) {
		for (OrderDetails od : orderDetailsList) {
			if (od.getProductID() == productId) return od;
		}
		return null;
	}
	
	
	public double calculateTotalPriceByItem(OrderDetails od) {
		return (od.getProductAmount() * od.getProductPrice());

	}
	
	
	public double calculateTotalPrice() {
		double sum = 0;
		for (OrderDetails od : orderDetailsList) {
			sum += calculateTotalPriceByItem(od);
		}
		return sum;
	}
	
	
	public ArrayList<OrderDetails> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(ArrayList<OrderDetails> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public void addToDetailsList(OrderDetails details) {

		boolean isExistFlag = false;
		
		for (int i = 0; i < orderDetailsList.size(); i++) {
			
			if (orderDetailsList.get(i).getProductID() == details.getProductID()) {
			
				isExistFlag = true;
				orderDetailsList.get(i).setProductAmount(orderDetailsList.get(i).getProductAmount() + 1);
				break;
			}
		}

		if (!isExistFlag) {
			details.setProductAmount(1);
			orderDetailsList.add(details);
		} else {
			System.out.println("Details list is empty");
		}

	}
	
	public int getTotalItems() {
		int totalItems = 0;
		for (OrderDetails od : orderDetailsList) {
			totalItems += od.getProductAmount();
		}
		return totalItems;
	}
	
	
	
	
}
