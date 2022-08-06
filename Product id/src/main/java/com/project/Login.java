package com.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "jdbc:mysql://localhost/ecommerce1";
		String username = "root";
		String password = "Sahithimorla@3112";

		response.setContentType("text/html");

		int pId = Integer.parseInt(request.getParameter("p_ID"));

		PrintWriter out = response.getWriter();

		String querys = "select * from eproduct where productid=?";
		out.print("<h1>Displaying the product Details...</h1>");
		out.print("<table border='1'><tr><th>productid</th><th>productname</th><th>productprice</th></tr>");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection dbCon = DriverManager.getConnection(url, username, password);
			PreparedStatement st = dbCon.prepareStatement(querys);

			st.setInt(1, pId);

			ResultSet result = st.executeQuery();

			while (result.next()) {

				out.print("<tr><td>");
				out.println(result.getInt(1));
				out.print("</td>");
				out.print("<td>");
				out.print(result.getString(2));
				out.print("</td>");
				out.print("<td>");
				out.print(result.getString(3));
				out.print("</td>");
				out.print("</tr>");

			}

		} catch (Exception e) {

			System.out.println("Some Issue : " + e.getMessage());

		}

	}

}