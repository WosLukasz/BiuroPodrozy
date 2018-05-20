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
		
	<!-- ==================================Dodaj nową ofertę================================================== -->
		<h1>Dodaj nową ofertę </h1>
		
		<div class="form">
		
			<div class="one">
				<div class="leftleft">
					<form title="dodajOferte" method="POST" action="addOffer" >
				
						<table border="0" align="center" cellpadding="4">
							<tr>
								<td>Nazwa Hotelu</td>
								<td><input type="text" name="nazwaHotelu" /><font
									color="red">*</font></td>
							</tr>
							<tr>
								<td>cena</td>
								<td><input type="text" name="cena" /><font
									color="red">*</font></td>
							</tr>
							<tr>
								<td>lokalizacja</td>
								<td><input type="text" name="lokalizacja" /><font
									color="red">*</font></td>
							</tr>
							<tr>
							<td>Wyzywienie</td>
							<td>
								<select name="wyzywienie">
									<option value="UltraAllinclusive" >Ultra Allinclusive</option>
									<option value="Allinclusive" >Allinclusive</option>
									<option value="HB+" >HB+</option>
									<option value="HB" >HB</option>
									<option value="Własne" >Własne</option>
								</select><font
									color="red">*</font>
							</td>
							<!--  
								<td>Wyzywienie</td>
								<td><input type="text" name="wyzywienie" /><font
									color="red">*</font></td>
									
									-->
							</tr>
							<tr>
								<td>data wyjazdu (YYYY-MM-DD)</td>
								<td><input type="text" name="dataWyjazdu" /><font
									color="red">*</font></td>
							</tr>
							<tr>
								<td>data powrotu (YYYY-MM-DD)</td>
								<td><input type="text" name="dataPowrotu" /><font
									color="red">*</font></td>
							</tr>
							<tr>
								<td>Ilosc Miejsc</td>
								<td><input type="text" name="iloscMiejsc" /><font
									color="red">*</font></td>
							</tr>
							<tr>
								<td>Opis wycieczki</td>
								<td>
									<textarea name="opisWycieczki"  rows="5" cols="40 ">Enter text here...</textarea>	
								</td>
							</tr>
							
							<tr>
								<td colspan="2"><input type="submit" value="Dodaj Ofertę" class="formButton"/> &nbsp;</td>
							</tr>
						</table>
					</form>
				
				</div>
			
				<div id="left">
					<form action="share" method="post">
						
									<strong>Słowa kluczowe:</strong><br/>
							<input type="text" name="slowa" value="${slowa}" />
							<br/>
							<br/>
							
							
							<strong>Lokalizacja:</strong><br/>
							<input type="text" name="lokalizacja" value="${lokalizacja}" />
							<br/>
							<br/>
							
							<strong>Cena:</strong>
							<br/>
							<input type="text" placeholder="Od"name="cenamin" value="${cenamin}" /><br/>
							<input type="text"  placeholder="Do"name="cenamax" value="${cenamax}" />
							<br/>
							<br/>
							
							<!--  
							Wyzywienie:<br/>
							<select name="wyzywienie">
								<option value="all" >Wszystkie</option>
								<option value="Ultra Allinclusive" >Ultra Allinclusive</option>
								<option value="Allinclusive" >Allinclusive</option>
								<option value="HB+" >HB+</option>
								<option value="HB" >HB</option>
								<option value="Własne" >Własne</option>
							</select>
							<br/>
							<br/>
							-->
			
							<strong>Data wyjazdu:</strong><br/>
							<input type="text" placeholder="(YYYY-MM-DD)" name="datamin" value="${datamin}" /> 
							<br/>
							<br/>
							<strong>Data powrotu:</strong><br/>
							<input type="text" placeholder="(YYYY-MM-DD)" name="datamax" value="${datamax}" /> 
							<br/>
							<br/>
							
							<strong>Sortuj względem:</strong><br/>
							<select name="sort">
								<option value="ID_OFERTY" >Domyślnie</option>
								<option value="Cena" >Cena</option>
								<option value="Nazwa_HOTELU" >Nazwa</option>
							</select>
							<br/>
							<br/>
							
							<input type="hidden" name="path" value="AddOffer">
							
							
							<input type="submit" value="Wyszukaj" />
							
					</form>
					
				</div>
			
			</div>
				
			<div id="right">
				
				<c:forEach items="${list}" var="o">
	
					<div class="of">
					
								<div class="zdj">
									<img src="${o.zdj}" width="" height="" />
								</div>
								
								<p style="font-size:10px;">nr oferty: ${o.idOferty}</p>
								
								<p style=" font-size:22px; font-weight:bold;color:#FFC005; ">${o.nazwaHotelu}</p>
								<p style="font-size:20px; font-weight:bold; color:#2398E4; ">Cena: ${o.cena} zł/netto<br/>
									<span style="font-size:15px; font-weight:bold; color:#212121; ">Cena: ${o.cenaBrutto} zł/brutto</span>
								</p>
								
								<div class="info" style="float:left;">
									
									
									<strong>Lokalizacja:</strong> ${o.lokalizacja}<br/>
									<strong>Wyżywienie:</strong> ${o.wyzywienie}<br/>
									<strong>Data wyjazdu:</strong> ${o.dataWyjazdu}<br/>
									<strong>Data powrotu:</strong> ${o.dataPowrotu}<br/>
									
									<p style=" font-weight:bold; ">Opis wycieczki:</p>
									<p>${o.opisWycieczki}</p>
			
								
								</div>
								
								<div class="infor" style="float:right; text-align:right; color:red; font-size:20px; font-weight:bold;">
									Pozostała ilość miejsc: ${o.iloscMiejsc} <br/>
									
									
								</div>
							
							<div style="clear:both;"></div>
	
					</div>
	
				</c:forEach>
	
			</div>
					
			<div style="clear:both;"></div>
		
		</div>
			
	</div>
	
	<div id="footer" style="clear:both; padding-top:20px;">

        <p style="text-align:center;">Wykonał <strong>Łukasz Woś</strong> &copy; Wszelkie prawa zastrzeżone</p>

      	</div>
	
</body>
</html>