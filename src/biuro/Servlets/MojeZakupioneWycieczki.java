package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biuro.Models.Klient;
import biuro.Models.OfertaWyc;
import biuro.Models.Pracownik;
import biuro.Models.Rezerwacja;
import biuro.databaseConnectionUtils.MyConnectionUtils;

/**
 * Servlet implementation class MojeZakupioneWycieczki
 */
@WebServlet("/mojeWyc")
public class MojeZakupioneWycieczki extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		
		
		request.setCharacterEncoding("utf-8");
		
		if(request.isRequestedSessionIdValid())
		{
			System.out.println("wesz³em !! ");
			HttpSession session = request.getSession();
			Klient k = (Klient) session.getAttribute("zalogowany");
				
				//List<OfertaWyc> lista = null;
				
				Map<Rezerwacja,OfertaWyc> mapa = new HashMap<>();
				Map<Rezerwacja,List<Pracownik>> mapa2 = new HashMap<>();
				try {
					mapa =  MyConnectionUtils.znajdzRezerwacjeKlienta(k);
					
					Set<Map.Entry<Rezerwacja,OfertaWyc>> set = mapa.entrySet();
					
					
					for(Map.Entry<Rezerwacja,OfertaWyc> me : set)
					{
						mapa2.put(me.getKey(), MyConnectionUtils.dajkWszystkichPracownikowKontaktowych(me.getKey()));

					}
					
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("map", mapa);
				request.setAttribute("map2", mapa2);
				
				RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/zakupione.jsp");
				disp.forward(request, response);
		}else
		{
			System.out.println("Musisz byæ zalogowany ¿eby przejœæ do tej zak³adki");
			request.setAttribute("errorString" , "Musisz byæ zalogowany ¿eby przejœæ do tej zak³adki");
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");

			dispatcher.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
