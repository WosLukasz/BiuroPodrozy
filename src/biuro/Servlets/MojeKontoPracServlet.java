package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biuro.Models.Klient;
import biuro.Models.Pracownik;
import biuro.Models.Rezerwacja;
import biuro.databaseConnectionUtils.MyConnectionUtils;

/**
 * Servlet implementation class MojeKontoPracServlet
 */
@WebServlet("/main")
public class MojeKontoPracServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String s = request.getParameter("errorString");
		request.setCharacterEncoding("utf-8");
		//request.setAttribute("errorString",s);
		
		
		HttpSession session = request.getSession();
		
		Pracownik p = (Pracownik) session.getAttribute("zalogowanyPrac");
		
		
		if(p!= null)
		{
			int id = p.getIdPracownika();
			Map<Klient, Rezerwacja> mapa = null;
			
			try {
				
				mapa = MyConnectionUtils.dajWszystkichKlientowPracownika(id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			request.setAttribute("mapa", mapa);
			
		}
		
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pracArea.jsp");
		disp.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
		
	}

}
