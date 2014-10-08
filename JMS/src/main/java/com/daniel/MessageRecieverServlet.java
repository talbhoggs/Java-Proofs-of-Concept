package com.daniel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ejb.*;

/**
 * Servlet implementation class MessageRecieverServlet
 */
@WebServlet("/sendTwitterMessage")
public class MessageRecieverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	TwitterSendMessage twitterMessageService;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MessageRecieverServlet() {
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
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();

		pw.write("<html><body>");

		pw.write("By: " + request.getParameter("by") + ", Message: " + request.getParameter("content") );
		
		String by =  request.getParameter("by");
		String content = request.getParameter("content");
		
		twitterMessageService.sendTwitterMessageToJMSListener(by, content);

		pw.write("</body></html>");

		pw.close();

	}

}
