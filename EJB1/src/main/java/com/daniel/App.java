package com.daniel;

import java.io.IOException;
import java.util.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class App
 */
public class App extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public App() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello World!");
		// EJBContainer ejbContainer = EJBContainer.createEJBContainer();

		Object object = null;
		try {
			Context context = new InitialContext();
			// LongTimeService bean = context.lookup("LongTimeService");
			object = context
					.lookup("java:global/ejbweb/WineSearch!com.daniel.WineSearchRemote");
		} catch (NamingException e) {
			System.out.println("NameExeption in getContext and Lookup");
			e.printStackTrace();
		}

		WineSearchRemote wineSearchBean = (WineSearchRemote) object;
		List wines = wineSearchBean.wineSearch("Red");
		for (Object wine : wines) {
			System.out.println((String) wine);
		}
		
		Object object2 = null;
		try {
			Context context = new InitialContext();
			// LongTimeService bean = context.lookup("LongTimeService");
			object2 = context
					.lookup("java:global/ejbweb/WineSearch!com.daniel.WineSearchLocal");
		} catch (NamingException e) {
			System.out.println("NameExeption in getContext and Lookup");
			e.printStackTrace();
		}
		
		WineSearchLocal wineSearchBean2 = (WineSearchLocal) object2;
		List wines2 = wineSearchBean2.wineSearch("White");
		for (Object wine : wines2) {
			System.out.println((String) wine);
		}
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
