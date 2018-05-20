package biuro.Servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biuro.Models.Klient;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String s = request.getParameter("errorString");
		
		request.setAttribute("errorString",s);
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		disp.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		
		//int id = Integer.parseInt(request.getParameter("id"));
		String login = request.getParameter("login");
		String haslo = request.getParameter("haslo");

		Klient k = null;
		boolean hasErrors = false;
		String stringError = null;

		if (login==null || haslo == null || login.length()==0 || haslo.length()==0) {
			hasErrors = true;
			stringError = "Login i has³o s¹ wymagane";
		} else {

			try {

				k = biuro.databaseConnectionUtils.MyConnectionUtils.znajdzKlienta(login, haslo);

				if (k == null) {
					hasErrors = true;
					stringError = "Niepoprawne login i/lub has³o";
				}

			} catch (SQLException | ClassNotFoundException eSql) {
				eSql.printStackTrace();
				hasErrors = true;
				stringError = eSql.getMessage();
			}
		}

		if (hasErrors) {
			k = new Klient();
			k.setLogin(login);
			k.setHaslo(haslo);

			request.setAttribute("errorString", stringError);
			request.setAttribute("klient", k);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");

			dispatcher.forward(request, response);
		} else {
			
			request.getSession().invalidate();
			
			HttpSession session = request.getSession();
			
			session.setAttribute("zalogowany", k);
			session.setAttribute("zal", 1);
			
			response.sendRedirect(request.getContextPath() );
		}

	}

}
