package controllers;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import beans.Order;
import beans.OrderDetails;
import dao.ConnectionWorker;
import dao.OrderDetailsDAO;
import dao.OrdersDAO;
import utils.DatabaseUtil;
import utils.OrderDetailsList;
import utils.OrderStatus;

@WebServlet("/controller")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	OrderDetailsList detailsList = new OrderDetailsList();
	// Order ID
	int orderId = 0;

	public HomeController() {
		super();
		ConnectionWorker cw = ConnectionWorker.connectMSSQL();
		orderId = cw.getMaxOderId() + 1;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("message", "");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		switch (action) {
		case "dologin":
			doLogin(request, response);
			return;
		case "cart":
			addToCart(request, response);
			return;
		case "checkout":
			doCheckOut(request, response);
			return;
		default:
			toHomePage(request, response);
			break;
		}
	}

	private void doCheckOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		ConnectionWorker cw = ConnectionWorker.connectMSSQL();
		Order order = (Order) session.getAttribute("order");

		// write Order to database
		OrdersDAO od = new OrdersDAO(cw, order);
		try {
			if (!od.isOrderIdExist(order.getId())) {
				od.writeToDB();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Write OrderDetails to Database
		OrderDetailsDAO odd = new OrderDetailsDAO(cw, detailsList);
		odd.writeToDatabase();
		
		getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);

	}

	private void toHomePage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url;
		String message = null;

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		ConnectionWorker cw = ConnectionWorker.connectMSSQL();

		Account account = cw.getAccountInfo(email);

		HttpSession session = request.getSession();

		try {
			if (cw.doLogin(account)) {
				url = "/index.jsp";
				session.setAttribute("account", account);
				// TODO: debug
				System.out.println("Login success. Account: " + account);
			} else {
				url = "/login.jsp";
				message = "Email address or password not recognized";

				// TODO: debug
				System.out.println("Login failed");
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			url = "/login.jsp";
			message = "Database error: please try again later";
		}

		session.setAttribute("email", email);
		session.setAttribute("account", account);
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher(url).forward(request, response);

	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;

		String productCode = request.getParameter("productCode");
		String productPrice = request.getParameter("productPrice");

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");

		ConnectionWorker cw = ConnectionWorker.connectMSSQL();

		if (email == null) {
			email = "duongdt@fpt.com.vn"; // default
		}

		Account account = cw.getAccountInfo(email);

		if (account != null) {
			// TODO: debug
			System.out.println(account);

			int orderStatus = OrderStatus.ORDER_PROCESSING;
			LocalDate orderDate = LocalDate.now();
			String discountCode = "";
			String address = cw.getAccountInfo(email).getAddress();

			Order order = new Order(orderId, email, orderStatus, orderDate, discountCode, address);

			// Create new OrderDetails
			OrderDetails details = new OrderDetails(order.getId(), Integer.parseInt(productCode), 1,
					Double.parseDouble(productPrice));
			detailsList.addToDetailsList(details);

			session.setAttribute("detailsList", detailsList);
			session.setAttribute("order", order);

			url = "/cart.jsp";
			session.setAttribute("productCode", productCode);
		} else {
			System.out.println("Account instance is null");
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
