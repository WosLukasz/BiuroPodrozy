package biuro.Servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
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
 * Servlet implementation class ShareServlet
 */
@WebServlet("/share")
public class ShareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String wzorzec = request.getParameter("slowa");
		String lokalizacja = request.getParameter("lokalizacja");
		String cenamin = request.getParameter("cenamin");
		String cenamax = request.getParameter("cenamax");
		//String wyzywienie = request.getParameter("wyzywienie");
		
		String datamin = request.getParameter("datamin");
		String datamax = request.getParameter("datamax");
        
        String sort = request.getParameter("sort");
        
		List<OfertaWyc> lista = null;
		
		try {
			lista = MyConnectionUtils.wykonajZlozoneZapytanie(wzorzec, lokalizacja, cenamin, cenamax , datamin, datamax, sort);
			
			
			Comparator<OfertaWyc> commp = null;
			
			if("ID_OFERTY".equals(sort))
			{
				commp = new Comparator<OfertaWyc>() {
		            @Override
		            public int compare(OfertaWyc o1, OfertaWyc o2) {
		                if(o1.getIdOferty()<o2.getIdOferty())
		                {
		                    return -1;
		                }else if(o1.getIdOferty()>o2.getIdOferty())
		                {
		                    return 1;
		                }else
		                {
		                	return 0;
		                }
		            }
		        };
			}else if("Cena".equals(sort))
			{
				commp = new Comparator<OfertaWyc>() {
		            @Override
		            public int compare(OfertaWyc o1, OfertaWyc o2) {
		                if(o1.getCena()<o2.getCena())
		                {
		                    return -1;
		                }else if(o1.getCena()>o2.getCena())
		                {
		                    return 1;
		                }else
		                {
		                	return 0;
		                }
		            }
		        };
			}else if("Nazwa_HOTELU".equals(sort))
			{
				commp = new Comparator<OfertaWyc>() {
		            @Override
		            public int compare(OfertaWyc o1, OfertaWyc o2) {
		                return o1.getNazwaHotelu().compareTo(o2.getNazwaHotelu());
		            }
		        };
			}
			
			Collections.sort(lista, commp);
			

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		request.setAttribute("list", lista);
		String path = String.valueOf(request.getParameter("path"));
		request.setAttribute("slowa", wzorzec);
		request.setAttribute("lokalizacja", lokalizacja);
		request.setAttribute("cenamin", cenamin);
		request.setAttribute("cenamax", cenamax);
		request.setAttribute("datamin", datamin);
		request.setAttribute("datamax", datamax);
		
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/"+path+".jsp");
		disp.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
