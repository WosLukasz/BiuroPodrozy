package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class AddOfferServlet
 */
@WebServlet("/addOffer")
public class AddOfferServlet extends HttpServlet {
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

		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/AddOffer.jsp");
		disp.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String nazwaHotelu = request.getParameter("nazwaHotelu");
		String cena = request.getParameter("cena");
		String lokalizacja = request.getParameter("lokalizacja");
		String wyzywienie = request.getParameter("wyzywienie");
		String dataWyjazdu = request.getParameter("dataWyjazdu");
		String dataPowrotu = request.getParameter("dataPowrotu");
		String iloscMiejsc = request.getParameter("iloscMiejsc");
		String opisWycieczki = request.getParameter("opisWycieczki");
		
		
		if(	nazwaHotelu != null && nazwaHotelu.length()!=0 && 
			cena != null && cena.length()!=0 &&
			lokalizacja != null && lokalizacja.length()!=0 &&
			wyzywienie != null && wyzywienie.length()!=0 &&
			dataWyjazdu != null && dataWyjazdu.length()!=0 &&
			dataPowrotu != null && dataPowrotu.length()!=0 &&
			iloscMiejsc != null && iloscMiejsc.length()!=0 &&
			opisWycieczki != null  )
		{
			
			OfertaWyc o = new OfertaWyc();
			
			o.setNazwaHotelu(nazwaHotelu);
			o.setCena(Integer.parseInt(cena));
			o.setLokalizacja(lokalizacja);
			o.setWyzywienie(wyzywienie);
			o.setIloscMiejsc(Integer.parseInt(iloscMiejsc));
			o.setOpisWycieczki(opisWycieczki);
			if(o.getIloscMiejsc()>0)
			{
				o.setDostepnoscOf(1);
			}else
			{
				o.setDostepnoscOf(0);
			}
			
			
			
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	        java.util.Date parsed = null;
	        try {
				parsed = format.parse(dataWyjazdu);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        o.setDataWyjazdu(new java.sql.Date(parsed.getTime()));
	        
	        try {
				parsed = format.parse(dataPowrotu);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        o.setDataPowrotu(new java.sql.Date(parsed.getTime()));
			
	        try {
				biuro.databaseConnectionUtils.MyConnectionUtils.dodajOferte(o);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        String stringError = "Pomyœlnie dodano ofertê";
			request.setAttribute("errorString", stringError);
	        
			doGet(request,response);
			/*
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/AddOffer.jsp");
				disp.forward(request, response);
		*/

			
		}else
		{
			String stringError = "Pola musz¹ byæ wype³nione";

			
			request.setAttribute("errorString", stringError);
			 
			doGet(request,response);
			/*
			RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/AddOffer.jsp");
				disp.forward(request, response);
		*/
		}
		
		
		
		
		
		
		
	}

}
