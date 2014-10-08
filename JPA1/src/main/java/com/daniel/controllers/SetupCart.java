package com.daniel.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.*;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daniel.models.*;
import com.daniel.service.*;

/**
 * Servlet implementation class Main
 */
@WebServlet("/SetupCart")
public class SetupCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ShoppingCartLocal sc;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupCart() {
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
		//Stateful bean ShoppingCart (sc)
		sc.setShopperId(); // set a random shopper id that will be maintained over the session due the bean being stateful.
		
		pw.write("The shopper id is: " + sc.getShopperId() + "<br />This num of items in the shopping cart: " + sc.getNum());

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
