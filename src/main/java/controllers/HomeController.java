package controllers;

import java.io.IOException;

import java.sql.Connection;
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
import dao.OrdersDAO;
import utils.DatabaseUtil;
import utils.OrderDetailsList;
import utils.OrderStatus;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/controller")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Product list added to cart
	List<String> cartItems = new ArrayList<>();
	// Order details
	OrderDetailsList detailsList = new OrderDetailsList();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("message", "");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get action
		String action = request.getParameter("action");
		String url = "/index.jsp";
		String message = "";

		if (action == null) {
//			action = "dologin"; // default
			// TODO: debug
			System.out.println("action null ");
		}

		// Connect to database
		ConnectionWorker cw = new ConnectionWorker(DatabaseUtil.SQLSERVER_URL, DatabaseUtil.SQLSERVER_USERNAME,
				DatabaseUtil.SQLSERVER_PASSWORD, DatabaseUtil.SQLSERVER_DRIVE);
		Connection conn = cw.getConnection();

		// Create account instance
		Account account = null;

		// Create session
		HttpSession session = request.getSession();

		session.setAttribute("cw", cw);

		// Login
		if (action.equals("dologin")) {
			doLogin(action, request, account, cw, url, session, message);

			// Add to cart
		} else if (action.equals("cart")) {
			// TODO: debug
			System.out.println("action " + action);

			String productCode = request.getParameter("productCode");
			String productPrice = request.getParameter("productPrice");
			cartItems.add(productCode);

			OrderDetails details = new OrderDetails(Integer.parseInt(productCode), Double.parseDouble(productPrice));

			// Add to Order Details List
			detailsList.addToDetailsList(details);

			session.setAttribute("cartItems", cartItems);
			session.setAttribute("detailsList", detailsList);
			
			String email = (String) session.getAttribute("email");
			// TODO: debug
			System.out.println("Email:" + email);
			if (email == null) {
				email = "duongdt@fpt.com.vn"; // default
			}

			account = cw.getAccountInfo(email);

			if (account != null) {
				// TODO: debug
				System.out.println(account);

				int id = cw.getMaxOderId() + 1;
				int orderStatus = OrderStatus.ORDER_PROCESSING;
				LocalDate orderDate = LocalDate.now();
				String discountCode = "";
				String address = cw.getAccountInfo(email).getAddress();

				Order order = new Order(id, email, orderStatus, orderDate, discountCode, address);

				// write Order to database
				OrdersDAO od = new OrdersDAO(cw, order);
//				od.writeToDB();
				url = "/cart.jsp";

				session.setAttribute("productCode", productCode);

			} else {
				System.out.println("Account instance is null");
			}

		} else {
			url = "/index.jsp";
			System.out.println(action);
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	private void doLogin(String action, HttpServletRequest request, Account account, ConnectionWorker cw, String url,
			HttpSession session, String message) {
		// TODO: debug
		System.out.println("action do login" + action);

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		account = cw.getAccountInfo(email);

		System.out.println(account.getMail() + ": " + account.getPassword());

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
			// TODO: debug
			System.out.println("Database failed");

		}

		session.setAttribute("email", email);
		request.setAttribute("message", message);
	}

	public void printCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		System.out.println("Cookies");
		for (int i = 0; i < cookies.length; i++) {
			String name = cookies[i].getName();
			String value = cookies[i].getValue();
			System.out.println("name :" + name + "| value:" + value);
		}
	}

}
