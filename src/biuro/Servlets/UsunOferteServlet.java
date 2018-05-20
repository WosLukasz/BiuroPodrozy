package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biuro.Models.OfertaWyc;
import biuro.databaseConnectionUtils.MyConnectionUtils;
import biuro.exception.MyException;

/**
 * Servlet implementation class UsunOferteServlet
 */
@WebServlet("/deleteOffer")
public class UsunOferteServlet extends HttpServlet {
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
		
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/DeleteOffer.jsp");
		disp.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		
		String idOfs = request.getParameter("idOferty");
		int idOf=0;
		String stringError = null;
		
		if(idOfs != null && idOfs.length()!=0)
		{
			idOf = Integer.parseInt(idOfs);
			
			try {
				MyConnectionUtils.usunOferte(idOf);
				stringError ="Pomyœlnie usuniêto oferte";
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				stringError ="Coœ posz³o nie tak";
				e.printStackTrace();
			} catch (MyException e) {
				// TODO Auto-generated catch block
				stringError = e.getS();
			}

		}
		
		request.setAttribute("errorString", stringError);
        
		doGet(request,response);
		/*
        RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/pracArea.jsp");
		disp.forward(request, response);
		*/
		
	}

}
