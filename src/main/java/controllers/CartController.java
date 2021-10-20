package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import beans.Order;
import dao.ConnectionWorker;
import dao.OrdersDAO;
import utils.DatabaseUtil;
import utils.OrderStatus;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("action");
		HttpSession session = request.getSession();
		
		ConnectionWorker cw = (ConnectionWorker) session.getAttribute("cw");
		 
		String url = "/cart.jsp";
		
		if (action == null) {
			action = "cart";
		}
		
		if (action.equals("cart")) {
			String productCode = request.getParameter("productCode");
			
			System.out.println(productCode);
		 }
		
		
		
		System.out.println(action);
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
