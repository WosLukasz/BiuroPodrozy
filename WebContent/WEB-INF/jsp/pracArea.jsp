<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Biuro Podrozy</title>

	<link rel="icon" href="img/samolocik.ico">
	<link rel="stylesheet" href="css/styleP.css" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Courgette|Roboto:400,700,900,900i&amp;subset=latin-ext" rel="stylesheet">
	
	<style>
		
		table {
			background: #cde;
			font-family: sans-serif;
			font-size: 18px;
			border: 1px solid #cde;
			padding: 2px;
			border-spacing: 0 1px;
			text-align: left;
		}
		
		tr:nth-child(odd) td {
			background: #fff;
		}
		tr:nth-child(even) td {
			background: #eee;
		}
		
		th {
			background: #cde;
			padding: 5px;
		}
		td {
			padding: 5px;
		}
		
		td:nth-child(5) {
			text-align: center;
		}
		a {
			color: #47c;
		}
	
	
	
	</style>
	
	
</head>
<body>

		<c:url value="//main" var="strG" scope="page" />
	<a href="${strG}">
		<div id="logo" style="width: 620px;">
		
			<h1 > <span style="color:#FFC005; font-weight:bold;">  Dream </span><img src="img/samolocik.png" height="70px"width="70px"> Travel <span style="font-size:20px; color:#212121; font-family: 'Roboto', sans-serif;">Edit</span></h1>

		</div>
		</a>
		
		<div class="nav">
			<div id="menu">
			<c:url value="/main" var="main" scope="page" />
			<c:url value="/addOffer" var="add" scope="page" />
			<!-- ZMIENIC JESZCZE ! -->
			<c:url value="/editOffer" var="edit" scope="page" />
			<c:url value="/deleteOffer" var="del" scope="page" />
			<c:url value="/deleteReservation" var="delR" scope="page" />
			
			<!-- POKAZUJE JACY KLIENCI SA DO NAS PRZYPISANI -->
			<c:url value="/addOffer" var="add" scope="page" />
			<!-- POWROT DO INDEX.JSP -->
			<c:url value="/index" var="back" scope="page" />
			
				<a href="${main}"><div class="nButton">Moje konto</div></a>
				<a href="${add}"><div class="nButton">Dodaj ofertę</div></a>
				<a href="${edit}"><div class="nButton">Edytuj ofertę</div></a>
				<div style="clear:both;"></div>
				<a href="${del}"><div class="nButton">Usuń ofertę</div></a>
				<a href="${delR}"><div class="nButton">Usuń rezerwację klienta</div></a>
				<a href="${back}"><div class="nButton">Wyjscie z edycji</div></a>
				<div style="clear:both;"></div>
			</div>
		</div>


	<div id="content">
		<c:if test="${sessionScope.zalP eq '1'}">
				<c:url value="/logout" var="logout" scope="page" />
				<div class="zalogowany">
					<strong>Jesteś zalogowany jako pracownik:</strong> ${sessionScope.zalogowanyPrac.imie} ${sessionScope.zalogowanyPrac.nazwisko}<br/>
					<strong>Twoje id:</strong> ${sessionScope.zalogowanyPrac.idPracownika}<br/>
					<strong>Pracujesz w dziale specjalizującym się w:</strong>
					<c:if test="${sessionScope.zalogowanyPrac.idSpec eq '1'}"> 
							opłaty za wycieczki
						</c:if>
						<c:if test="${sessionScope.zalogowanyPrac.idSpec eq '2'}"> 
							organizacja wycieczek
						</c:if>
						<c:if test="${sessionScope.zalogowanyPrac.idSpec eq '3'}"> 
							zwroty wycieczek
						</c:if>
						<a href="${logout}"><div class="logout">wyloguj się</div></a>
				</div>
		</c:if>
	
		<p style="color: red;">${errorString}</p>
		
		
		<h1>Rezerwacje klientów, które są do ciebie przydzielone:</h1>
		
		
		<div class="list">
		
			<table  align="center" >
				<tr>
					<td><strong>Id rezerwacji</strong></td>
					<td><strong>Data rezerwacji</strong></td>
					<td><strong>Id ofery</strong></td>
					<td><strong>Czy zaplacone</strong></td>
					<td>|</td>
					<td><strong>Id Klienta</strong></td>
					<td><strong>Imie</strong></td>
					<td><strong>Nazwisko</strong></td>
					<td><strong>nr dowodu</strong></td>
					<td><strong>Login</strong></td>
				</tr>
			
				<c:forEach items="${mapa}" var="entry">
				
					<tr>
						<td>${entry.value.idRez}</td>
						<td>${entry.value.dataRez}</td>
						<td>${entry.value.idOferty}</td>
						<td>
			
							<c:if test="${entry.value.czyZaplacone == 1}">	
								Tak
							</c:if>
							
							<c:if test="${entry.value.czyZaplacone != 1}">	
								Nie
							</c:if>
						</td>
						<td></td>
						<td>${entry.key.idKlienta}</td>
						<td>${entry.key.imie}</td>
						<td>${entry.key.nazwisko}</td>
						<td>${entry.key.nrDowodu}</td>
						<td>${entry.key.login}</td>	
					</tr>
		
				</c:forEach>
				</table>
		</div>
	</div>
	
	<div id="footer" style="clear:both; padding-top:20px;">

        <p style="text-align:center;">Wykonał <strong>Łukasz Woś</strong> &copy; Wszelkie prawa zastrzeżone</p>

      	</div>

</body>
</html>