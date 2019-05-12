package com.Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
public class JdbcServlet extends HttpServlet {
	/**
	 * The doGet method of the servlet. <br>
	 * This method is called when a form has its tag value method equals to get.
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
	       String username=request.getParameter("username");
	       String password=request.getParameter("password");
	   	Connection con = null;
	       try {
				Class.forName("com.mysql.jdbc.Driver");
				con = (Connection) DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/userdb", "root", "admin");
				Statement sta = (Statement) con.createStatement();
				int n = sta.executeUpdate("insert user values ('"+username+"','"+password+"') ");
				if (n > 0) {
					request.getRequestDispatcher("/SS").forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.print("没找到");
			} catch (SQLException sqle) {
				System.out.print("连接异常");
			}
		
		}
}
