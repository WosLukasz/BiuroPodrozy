<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<!--  <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Biuro Podróży</title>

	<link rel="icon" href="img/samolocik.ico">
	<link rel="stylesheet" href="css/style.css" type="text/css" />
	<link href="https://fonts.googleapis.com/css?family=Courgette|Roboto:400,700,900,900i&amp;subset=latin-ext" rel="stylesheet">
	
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

</head>
<body>
	<div id="container">
	
	
	
	<c:url value="/index" var="strG" scope="page" />
	<a href="${strG}">
		<div id="logo">
		
			<h1 > <span style="color:#FFC005; font-weight:bold;">  Dream </span><img src="img/samolocik.png" height="70px"width="70px"> Travel</h1>

		</div>
		</a>
		<div class="nav">
			<div id="menu">
			<c:url value="/login" var="log" scope="page" />
			<c:url value="/rejestracja" var="rej" scope="page" />
			<c:url value="/mojeWyc" var="moje" scope="page" />
			
			<c:if test="${sessionScope.zal!=1}">
				<c:url value="/login" var="moje" scope="page" >
					<c:param name="errorString" value="Musisz być zalogowany żeby przejść do tej zakładki" />
				</c:url>
			</c:if>
			
			<c:if test="${sessionScope.zal==1}">
				<c:url value="/mojeWyc" var="moje" scope="page" />
			</c:if>
			 
			<c:url value="/pracownicy" var="prac" scope="page" />
			
				<a href="${strG}"><div class="nButton">Strona główna</div></a>
				<c:if test="${sessionScope.zal!=1}">
					<a href="${log}"><div class="nButton">Zaloguj się</div></a>
				</c:if>
				<c:if test="${sessionScope.zal==1}">
				<c:url value="/logout" var="logout" scope="page" />
					<a href="${logout}"><div class="nButton">wyloguj się</div></a>
				</c:if>
				<a href="${rej}"><div class="nButton">Zarejestruj się</div></a>
				<a href="${moje}"><div class="nButton">Zakupione wycieczki</div></a>
				<a href="${prac}"><div class="nButton">Strefa dla pracowników</div></a>
				<div style="clear:both;"></div>
			</div>
		</div>
		
		
		
		<div id="content">
		
			<c:if test="${sessionScope.zal eq '1'}">
			<c:url value="/logout" var="logout" scope="page" />
			<div class="zalogowany"><strong>Jesteś zalogowany jako:</strong> ${sessionScope.zalogowany.imie} ${sessionScope.zalogowany.nazwisko}
				<br/><strong>Twoj login to:</strong> ${sessionScope.zalogowany.login}
				<a href="${logout}"><div class="logout">wyloguj się</div></a>
			</div>
			
		
			</c:if>
		
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
				
				<input type="hidden" name="path" value="index">
				
				
				<input type="submit" value="Wyszukaj" />
				
			</form>
			
			</div>
			<div id="right">
				
				<c:forEach items="${list}" var="o">

					<c:url value="/select" var="ofertaLink" scope="page" >
						<c:param name="idOferty" value="${o.idOferty}" />
						<c:param name="cena" value="${o.cena}" />
						<c:param name="cenaBrutto" value="${o.cenaBrutto}" />
						<c:param name="nazwaHotelu" value="${o.nazwaHotelu}" />
						<c:param name="lokalizacja" value="${o.lokalizacja}" />
						<c:param name="wyzywienie" value="${o.wyzywienie}" />
						<c:param name="dataWyjazdu" value="${o.dataWyjazdu}" />
						<c:param name="dataPowrotu" value="${o.dataPowrotu}" />
						<c:param name="iloscMiejsc" value="${o.iloscMiejsc}" />
						<c:param name="opisWycieczki" value="${o.opisWycieczki}" />
						<c:param name="dostepnoscOf" value="${o.dostepnoscOf}" />
						<c:param name="zdj" value="${o.zdj}" />
					</c:url>
					
					
					<div class="of">
					<a href="${ofertaLink}">
						<div class="zdj">
							<img src="${o.zdj}" width="" height="" />
						</div>
					</a>
						
						<p style="font-size:10px;">nr oferty: ${o.idOferty}</p>
						
						<a href="${ofertaLink}"><p style=" font-size:22px; font-weight:bold;color:#FFC005; ">${o.nazwaHotelu}</p></a>
						<p style="font-size:20px; font-weight:bold; color:#2398E4; ">Cena: ${o.cena} zł/netto<br/>
							<span style="font-size:15px; font-weight:bold; color:#212121; ">Cena: ${o.cenaBrutto} zł/brutto</span>
						</p>
						
						
						<div class="info" style="float:left;">
							
							<strong>Lokalizacja:</strong> ${o.lokalizacja}<br/>
							<strong>Wyżywienie:</strong> ${o.wyzywienie}<br/>
							<strong>Data wyjazdu:</strong> ${o.dataWyjazdu}<br/>
							<strong>Data powrotu:</strong> ${o.dataPowrotu}<br/>
							<!-- 
							<p style=" font-weight:bold; ">Opis wycieczki:</p>
							<p>${o.opisWycieczki}</p>
							-->
						
						</div>
						
						<div class="infor" style="float:right; text-align:right; color:red; font-size:20px; font-weight:bold;">
							Pozostała ilość miejsc: ${o.iloscMiejsc} <br/>
							
							<!--  
							<c:if test="${sessionScope.zal eq '1'}">
								<c:url value="/kup" var="kup" scope="page" >
									<c:param name="id" value="${o.idOferty}" />
								</c:url>
								<a href="${kup}"><div class="kupButton">Rezerwuj tą wycieczkę</div></a>
							
							</c:if>
							<c:if test="${sessionScope.zal!=1}">
							
								<div class="kupButtonf" >Musisz być zalogowany by kupić wycieczkę</div>
								
							</c:if>
							-->
						</div>
						
						<div style="clear:both;"></div>

					</div>

				</c:forEach>

			</div>
			<div style="clear:both;">
			</div>

		</div>
		

	</div>

<div id="footer" style="clear:both; padding-top:20px;">

        <p style="text-align:center;">Wykonał <strong>Łukasz Woś</strong> &copy; Wszelkie prawa zastrzeżone</p>

      	</div>

</body>
</html>