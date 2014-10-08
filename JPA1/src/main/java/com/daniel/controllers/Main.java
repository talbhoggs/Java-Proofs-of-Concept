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
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@PersistenceUnit(unitName = "jpa1")
	private EntityManagerFactory emf;
	
	@EJB
	private ShoppingCartLocal sc;
	
	@EJB
	private ShopInfo si;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
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

		
		Object simpleUsersEjbObj = null;
		try {
			Context context = new InitialContext();
			// LongTimeService bean = context.lookup("LongTimeService");
			simpleUsersEjbObj = context
					.lookup("java:global/jpa1/SimpleUsers!com.daniel.service.SimpleUsersEJBLocal");
			pw.write("Successfully looked EJB...<br />");
		} catch (NamingException e) {
			System.out.println("NameExeption in getContext and Lookup");
			e.printStackTrace();
			pw.write("EJB lookup failed..." + "<br />");
		}
		
		SimpleUsersEJBLocal sUserEjbLocal  = (SimpleUsersEJBLocal) simpleUsersEjbObj;
		
		
		//EntityManager em = emf.createEntityManager();
		//SimpleUser user = (SimpleUser) em.createQuery("select u from SimpleUser u").getResultList().get(0);
		//pw.write("This is a test: " + user.getName()+"<br />");
		
		pw.write(sUserEjbLocal.getName(0) + " " + sUserEjbLocal.getAge(0) + "<br />");
		
		if (request.getSession().getAttribute("referenceToStatefulBeanShoppingCart") != null) {
			sc = (ShoppingCartLocal)request.getSession().getAttribute("referenceToStatefulBeanShoppingCart");
		}
		else {
			request.getSession().setAttribute("referenceToStatefulBeanShoppingCart", sc);
		}
		pw.write("The shopper id is: " + sc.getShopperId() + "<br />This num of items in the shopping cart: " + sc.getNum() + "<br />");

		pw.write("Shop info: num visitors: " + si.getNumVisitors() + ", " + si.getShopStatus());
		
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
