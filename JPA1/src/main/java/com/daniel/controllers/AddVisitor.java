package com.daniel.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import com.daniel.models.*;
import com.daniel.service.*;

import javax.ejb.*;

/**
 * Servlet implementation class AddItem
 */
@WebServlet("/addVisitor")
public class AddVisitor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ShopInfo si;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddVisitor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter pw = response.getWriter();
		pw.write("<html><body>");

		pw.write("Shop info: num visitors: " + si.getNumVisitors() + ", "
				+ si.getShopStatus() + "<br />");
		si.addVisitors(1);
		pw.write("After adding a visitor to the shop, the new number of visitors is: "
				+ si.getNumVisitors());
		pw.write("</body></html>");

		pw.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
