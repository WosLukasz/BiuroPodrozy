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

import biuro.Models.Klient;
import biuro.databaseConnectionUtils.MyConnectionUtils;


/**
 * Servlet implementation class RejestracjaServlet
 */
@WebServlet("/rejestracja")
public class RejestracjaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		RequestDispatcher disp = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/rejestracja.jsp");
		disp.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("utf-8");

		String dowod = request.getParameter("dowod");
		String userImie = request.getParameter("imie");
		String userNazwisko = request.getParameter("nazwisko");	
		String login = request.getParameter("login");	
		String haslo = request.getParameter("haslo");	

		Klient k = null;
		boolean hasErrors = false;
		String stringError = new String();

		

		if (login==null|| haslo==null|| dowod==null|| userImie==null || userNazwisko==null || login.length()==0|| haslo.length()==0|| dowod.length()==0|| userImie.length()==0 || userNazwisko.length()==0 ) {
			hasErrors = true;
			stringError = "Pola oznaczone gwiazdk¹ s¹ wymagane!";
		} else {

			try {
				k= new Klient();
				
				k.setNrDowodu(dowod);
				k.setImie(userImie);
				k.setNazwisko(userNazwisko);
				k.setHaslo(haslo);
				k.setLogin(login);
				
				 MyConnectionUtils.dodajKlienta(k);
				
			} catch (SQLException | ClassNotFoundException eSql) {
				eSql.printStackTrace();
				hasErrors = true;
				stringError = eSql.getMessage();
			}
		}

		if (hasErrors) {
			k = new Klient();
			
			k.setNrDowodu(dowod);
			k.setImie(userImie);
			k.setNazwisko(userNazwisko);
			k.setHaslo(haslo);
			k.setLogin(login);

			request.setAttribute("errorString", stringError);
			request.setAttribute("user", k);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/rejestracja.jsp");
			dispatcher.forward(request, response);
		} else {
			
			/*
			request.setAttribute("id", id);
			request.setAttribute("errorString", "Twój nr id potrzebny do logowania to: "+id+"<br/>Twoje haslo to wpisany nr dowodu.");
			*/
			request.setAttribute("errorString","Pomyœlna rejestracja");
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);


		}
		
		
		
	}

}
