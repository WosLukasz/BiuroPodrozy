package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biuro.Models.Klient;
import biuro.Models.OfertaWyc;
import biuro.databaseConnectionUtils.MyConnectionUtils;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns = {"/index"})
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	@SuppressWarnings("deprecation")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		List<OfertaWyc> lista = null;
		
		try {
			lista = MyConnectionUtils.dajWszystkieOferty();
			
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("list", lista);
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp");
		disp.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}