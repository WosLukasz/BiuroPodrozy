package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biuro.Models.OfertaWyc;
import biuro.databaseConnectionUtils.MyConnectionUtils;

/**
 * Servlet implementation class EditOfferServlet
 */
@WebServlet("/editOffer")
public class EditOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
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
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/EditOffer.jsp");
		disp.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String idOferty = request.getParameter("idOferty");
		String nazwaHotelu = request.getParameter("nazwaHotelu");
		String cena = request.getParameter("cena");
		String lokalizacja = request.getParameter("lokalizacja");
		String wyzywienie = request.getParameter("wyzywienie");
		String dataWyjazdu = request.getParameter("dataWyjazdu");
		String dataPowrotu = request.getParameter("dataPowrotu");
		String iloscMiejsc = request.getParameter("iloscMiejsc");
		String opisWycieczki = request.getParameter("opisWycieczki");
		
		
		 String stringError = null;
			
		
		
		
		if(idOferty != null && idOferty.length()!=0 &&
			nazwaHotelu != null  && nazwaHotelu.length()!=0 &&
			cena != null  && cena.length()!=0 &&
			lokalizacja != null  && lokalizacja.length()!=0 &&
			wyzywienie != null  /* && wyzywienie.length()!=0 */&&
			dataWyjazdu != null  && dataWyjazdu.length()!=0 &&
			dataPowrotu != null  && dataPowrotu.length()!=0 &&
			iloscMiejsc != null  && iloscMiejsc.length()!=0 &&
			opisWycieczki != null && opisWycieczki.length()!=0  )
		{
			
			
			try {
				MyConnectionUtils.edytujOferte(idOferty,nazwaHotelu,cena,lokalizacja,wyzywienie,dataWyjazdu,dataPowrotu,iloscMiejsc,opisWycieczki);
				stringError ="Pomyœlnie dodano ofertê";
			
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
		
		}else
		{
			stringError ="B³¹d wprowadzania danych";
		}
		request.setAttribute("errorString", stringError);
		
		doGet(request,response);
		/*
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/EditOffer.jsp");
		disp.forward(request, response);
		*/
		
		
		
	}

}
