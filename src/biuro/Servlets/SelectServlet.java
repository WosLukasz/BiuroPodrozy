package biuro.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biuro.Models.OfertaWyc;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/select")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String idOferty = request.getParameter("idOferty");
		String nazwaHotelu = request.getParameter("nazwaHotelu");
		String cena = request.getParameter("cena");
		String cenaBrutto = request.getParameter("cenaBrutto");
		String lokalizacja = request.getParameter("lokalizacja");
		String wyzywienie = request.getParameter("wyzywienie");
		String dataWyjazdu = request.getParameter("dataWyjazdu");
		String dataPowrotu = request.getParameter("dataPowrotu");
		String iloscMiejsc = request.getParameter("iloscMiejsc");
		String opisWycieczki = request.getParameter("opisWycieczki");
		String dostepnoscOf = request.getParameter("dostepnoscOf");
		String zdj = request.getParameter("zdj");
		
		
		request.setAttribute("idOferty", idOferty);
		request.setAttribute("nazwaHotelu", nazwaHotelu);
		request.setAttribute("cena", cena);
		request.setAttribute("cenaBrutto", cenaBrutto);
		request.setAttribute("lokalizacja", lokalizacja);
		request.setAttribute("wyzywienie", wyzywienie);
		request.setAttribute("dataWyjazdu", dataWyjazdu);
		request.setAttribute("dataPowrotu", dataPowrotu);
		request.setAttribute("iloscMiejsc", iloscMiejsc);
		request.setAttribute("opisWycieczki", opisWycieczki);
		request.setAttribute("dostepnoscOf", dostepnoscOf);
		request.setAttribute("zdj", zdj);
		
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/offer.jsp");
		disp.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
