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

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/userdb", "root", "admin");
			Statement sta = (Statement) con.createStatement();
			ResultSet rs = (ResultSet) sta.executeQuery("SELECT user from user WHERE user='"+ username + "'");
			if (rs.next()) {
				request.getRequestDispatcher("/SS").forward(request, response);
			} else {
				response.sendRedirect("/DengLu/ZhuCe.html");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			out.print("没找到");
		} catch (SQLException sqle) {

			out.print("连接异常");
		}
	}
}