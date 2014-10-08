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
@WebServlet("/addItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	private ShoppingCartLocal sc;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		PrintWriter pw = response.getWriter();
		pw.write("<html><body>");

		if (request.getSession().getAttribute("referenceToStatefulBeanShoppingCart") != null) {
			sc = (ShoppingCartLocal)request.getSession().getAttribute("referenceToStatefulBeanShoppingCart");
		}
		else {
			request.getSession().setAttribute("referenceToStatefulBeanShoppingCart", sc);
		}
		pw.write("The shopper id is: " + sc.getShopperId() + "<br />This num of items in the shopping cart: " + sc.getNum() + "<br />");
		sc.incrementNum();
		pw.write("After adding an item to the shopping cart, the new number of items is: " + sc.getNum());
		pw.write("</body></html>");
		
		pw.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
