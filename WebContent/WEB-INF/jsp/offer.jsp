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
	<link rel="stylesheet" href="css/styleOf.css" type="text/css" />
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
		<div class="mainInfo">
			
			<div class="left">
				
				<c:if test="${sessionScope.zal eq '1'}">
					<c:url value="/logout" var="logout" scope="page" />
					<div class="zalogowany"><strong>Jesteś zalogowany jako:</strong> ${sessionScope.zalogowany.imie} ${sessionScope.zalogowany.nazwisko}
						<br/><strong>Twoj login to:</strong> ${sessionScope.zalogowany.login}
						<a href="${logout}"><div class="logout">wyloguj się</div></a>
					</div>
				</c:if>
				
				<div class="info">
				
					<div class="infor" >
						Pozostała ilość miejsc: ${iloscMiejsc} <br/>
						
						<c:if test="${sessionScope.zal eq '1'}">
							<c:url value="/kup" var="kup" scope="page" >
								<c:param name="id" value="${idOferty}" />
							</c:url>
							<a href="${kup}"><div class="kupButton">Rezerwuj tą wycieczkę</div></a>
						
						</c:if>
						
						<c:if test="${sessionScope.zal!=1}">
						
							<div class="kupButtonf" >Musisz być zalogowany by kupić wycieczkę</div>
							
						</c:if>
					</div>
					
					<div class="rest">
				
				
					<p style="font-size:10px;">nr oferty: ${idOferty}</p>
					<p style=" font-size:22px; font-weight:bold;color:#FFC005; ">${nazwaHotelu}</p>
					<p style="font-size:20px; font-weight:bold; color:#2398E4; ">Cena: ${cena} zł/netto<br/>
							<span style="font-size:15px; font-weight:bold; color:#212121; ">Cena: ${cenaBrutto} zł/brutto</span>
						</p>
					
					
					<strong>Lokalizacja:</strong> ${lokalizacja}<br/>
					<strong>Wyżywienie:</strong> ${wyzywienie}<br/>
					<strong>Data wyjazdu:</strong> ${dataWyjazdu}<br/>
					<strong>Data powrotu:</strong> ${dataPowrotu}<br/>
				
					</div>
				</div>
					
			</div>
			
			<div class="right">
	
					<div class="zdj">
						<img src="${zdj}" width="" height="" />
					</div>
	
			</div>
			
			<div style="clear:both;"></div>
		
		</div>
		
		
		<div class="opis">
			<p style=" font-weight:bold; ">Opis wycieczki:</p>
					<p>${opisWycieczki}</p>
		
		</div>
		
		
	</div>

	<div id="footer" style="clear:both; padding-top:20px;">

     	<p style="text-align:center;">Wykonał <strong>Łukasz Woś</strong> &copy; Wszelkie prawa zastrzeżone</p>

   	</div>

</div>


</body>
</html>