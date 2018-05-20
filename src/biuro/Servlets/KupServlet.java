package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biuro.Models.Klient;
import biuro.databaseConnectionUtils.MyConnectionUtils;

/**
 * Servlet implementation class KupServlet
 */
@WebServlet("/kup")
public class KupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		Klient k = new Klient();
		
		int zal = (int) session.getAttribute("zal");
		
		if(zal!=1)
		{
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/index");
			disp.forward(request, response);
		}
		
		k = (Klient) session.getAttribute("zalogowany");
		
		int idWycieczki = Integer.parseInt(request.getParameter("id")); 
		
		System.out.println(idWycieczki);
		
		try {
			MyConnectionUtils.kupWycieczke(idWycieczki, k);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/mojeWyc");
		disp.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
