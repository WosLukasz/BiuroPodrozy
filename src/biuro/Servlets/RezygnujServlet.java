package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import biuro.databaseConnectionUtils.MyConnectionUtils;

/**
 * Servlet implementation class RezygnujServlet
 */
@WebServlet("/rezygnuj")
public class RezygnujServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String idRezs = request.getParameter("id");
		String idOfertys = request.getParameter("idOf");
		int idRez=0;
		int idOferty=0;
		
		if(idRezs != null && idRezs.length()!=0 && idOfertys != null && idOfertys.length()!=0)
		{
			idRez = Integer.parseInt(idRezs);
			idOferty = Integer.parseInt(idOfertys);
			
			try {
				MyConnectionUtils.usunRezerwacje(idRez,idOferty);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/mojeWyc");
		disp.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
