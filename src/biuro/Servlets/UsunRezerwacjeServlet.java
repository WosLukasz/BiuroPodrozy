package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;
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
 * Servlet implementation class UsunRezerwacjeServlet
 */
@WebServlet("/deleteReservation")
public class UsunRezerwacjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/deleteReservation.jsp");
		disp.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String idRezs = request.getParameter("idRez");
		
		String idOfertys = request.getParameter("idOf");
		int idRez=0;
		int idOferty=0;
		
		String stringError = null;
		
		if(idRezs != null && idRezs.length()!=0  && idOfertys != null && idOfertys.length()!=0)
		{
			idRez = Integer.parseInt(idRezs);
			idOferty = Integer.parseInt(idOfertys);
			
			try {
				MyConnectionUtils.usunRezerwacje(idRez,idOferty);
				stringError = "Pomyœlnie usuniêto rezerwacje";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				stringError ="Coœ posz³o nie tak";
				e.printStackTrace();
			}

		}
		
		request.setAttribute("errorString", stringError);
		
		doGet(request, response);
        /*
        RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pracArea.jsp");
		disp.forward(request, response);
		*/
	
	}

}
