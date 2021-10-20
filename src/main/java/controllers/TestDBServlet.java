package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.imageio.event.IIOReadWarningListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Order;
import dao.ConnectionWorker;
import dao.DatabaseDAO;
import dao.OrdersDAO;

/**
 * Servlet implementation class TestDBServlet
 */
@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("hello");
		
		// Connect to database
		ConnectionWorker cw = new ConnectionWorker(utils.DatabaseUtil.SQLSERVER_URL, "sa", "yourStrong(!)Password", utils.DatabaseUtil.SQLSERVER_DRIVE);
		Connection conn = cw.getConnection();
		if (conn != null) {
			try {
//				out.println("Connected");
//				DatabaseMetaData dm = (DatabaseMetaData)conn.getMetaData();
//				out.println(dm.getDriverName());
//				out.println(dm.getDriverVersion());
//				out.println(dm.getDatabaseProductName());
//				out.println(dm.getDatabaseProductVersion());
//				
//				// Test SQL query
//				Statement statement = conn.createStatement();
//				ResultSet resultSet
//                = statement.executeQuery("SELECT * FROM Account");
//				while(resultSet.next()) {
//					out.println(resultSet.getString("user_mail"));
//				}
			
//				out.println(cw.getMaxOderId());
				out.println(cw.getAccountInfo("duongdt@fpt.com.vn"));
				
				LocalDate today = LocalDate.now();
				
				Order order = new Order(2, "duongdt@fpt.com.vn", 2, today, "123", "dasd");
				OrdersDAO od = new OrdersDAO(cw, order);
				od.writeToDB();
				
			} finally {
				cw.closeConnection(conn);
			}
			
		} else {
			out.println("conn is null");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
