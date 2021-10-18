package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.ConnectionWorker;

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
				out.println("Connected");
				DatabaseMetaData dm = (DatabaseMetaData)conn.getMetaData();
				out.println(dm.getDriverName());
				out.println(dm.getDriverVersion());
				out.println(dm.getDatabaseProductName());
				out.println(dm.getDatabaseProductVersion());
				
				// Test SQL query
				Statement statement = conn.createStatement();
				ResultSet resultSet
                = statement.executeQuery("SELECT * FROM Account");
				while(resultSet.next()) {
					out.println(resultSet.getString("user_mail"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.println("Error connect to server");
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
