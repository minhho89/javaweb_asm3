package controllers;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import dao.ConnectionWorker;
import utils.DatabaseUtil;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/controller")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		String url = "/login.jsp";
		String message = "";

		if (action == null) {
			action = "dologin"; // default
		}

		// Connect to database
		ConnectionWorker cw = new ConnectionWorker(DatabaseUtil.SQLSERVER_URL, DatabaseUtil.SQLSERVER_USERNAME,
				DatabaseUtil.SQLSERVER_PASSWORD, DatabaseUtil.SQLSERVER_DRIVE);
		Connection conn = cw.getConnection();

		// Create account instance
		Account account = null;

		if (action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			account = new Account(email, password);
			
			System.out.println(account.getMail() + ": " + account.getPassword());
			
			HttpSession session = request.getSession();

			try {
				if (cw.doLogin(account)) {
					url = "/index.jsp";
					System.out.println("Login success");
				} else {
					url = "/login.jsp";
					message = "Email address or password not recognized";
					System.out.println("Login failed");
				}
			} catch (java.sql.SQLException e) {
				e.printStackTrace();
				url = "/login.jsp";
				message = "Database error: please try again later";
				System.out.println("Database failed");
				
			}

			session.setAttribute("account", account);
			request.setAttribute("message", message);

			getServletContext().getRequestDispatcher(url).forward(request, response);

		}
	}

}
