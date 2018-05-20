package biuro.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import biuro.Models.Klient;
import biuro.Models.Pracownik;

/**
 * Servlet implementation class LoginPracServlet
 */
@WebServlet("/pracownicy")
public class LoginPracServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String s = request.getParameter("errorString");
		request.setCharacterEncoding("utf-8");
		request.setAttribute("errorString",s);
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/loginPrac.jsp");
		disp.forward(request, response);
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		
		//int id = Integer.parseInt(request.getParameter("id"));
		String login= request.getParameter("login");
		String haslo = request.getParameter("haslo");


		Pracownik p = null;
		boolean hasErrors = false;
		String stringError = null;

		if (login==null || login.length()==0 || haslo==null || haslo.length()==0) {
			hasErrors = true;
			stringError = "login i haslo sa wymagane ";
		} else {

			try {
				
					p = biuro.databaseConnectionUtils.MyConnectionUtils.znajdzPracownika(login,haslo);
				if (p == null) {
					hasErrors = true;
					stringError = "Niepoprawne login i/lub haslo ";
				}

			} catch (SQLException | ClassNotFoundException eSql) {
				eSql.printStackTrace();
				hasErrors = true;
				stringError = eSql.getMessage();
			}
		}

		if (hasErrors) {
			p = new Pracownik();
			p.setLogin(login);

			request.setAttribute("errorString", stringError);
			request.setAttribute("pracownik", p);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/loginPrac.jsp");

			dispatcher.forward(request, response);
		} else {
			
			request.getSession().invalidate();
			
			HttpSession session = request.getSession();
			
			session.setAttribute("zalogowanyPrac", p);
			session.setAttribute("zalP", 1);
			
			//response.sendRedirect(request.getContextPath() );
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/main"); 

			dispatcher.forward(request, response);
			
		}
	
	}

}
