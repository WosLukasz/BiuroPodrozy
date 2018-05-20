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
	<link rel="stylesheet" href="css/styleZ.css" type="text/css" />
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
			
			<c:forEach items="${map}" var="entry">
	
	
			<div class="of">
				<div class="left">	
						<strong>Id rezerwacji:</strong> ${entry.key.idRez}<br>
						<strong>Data rezerwacji: </strong>${entry.key.dataRez}<br>
						<br>
						<br>
						<c:if test="${entry.key.czyZaplacone == 1}">	
						<div class="zaplacone" >Zapłaciłes już za wycieczkę</div><br>
						</c:if>
						
						<c:if test="${entry.key.czyZaplacone != 1}">	
						<strong style="color: red;">Musisz jeszcze zapłacić za tą wycieczkę</strong><br>
						
						<p style="font-size:20px; font-weight:bold; color:#2398E4; ">Cena: ${entry.value.cena} zł/netto<br/>
							<span style="font-size:15px; font-weight:bold; color:#212121; ">Cena: ${entry.value.cenaBrutto} zł/brutto</span>
						</p>
						
						</c:if>
						<br>
						
						<c:if test="${entry.key.czyZaplacone !=1}">	
							<c:url value="/zaplac" var="zap" scope="page" >
								<c:param name="id" value="${entry.key.idRez}" />
							</c:url>
							
							<a href="${zap}"><div class="zaplacButton">Zapłać za tą wycieczkę</div></a>
						
							<c:url value="/rezygnuj" var="rez" scope="page" >
								<c:param name="id" value="${entry.key.idRez}" />
								<c:param name="idOf" value="${entry.value.idOferty}" />
							</c:url>
							<a href="${rez}"><div class="rezygnujButton">Rezygnuj z rezerwacji</div></a>
						</c:if>
						
					<br><br>
						<c:forEach items="${map2}" var="entry2">
							<c:if test="${entry.key.idRez == entry2.key.idRez}"> 
								<c:forEach items="${entry2.value}" var="lista" varStatus="loop">
									<p><strong>Pracownik kontaktowy:</strong> ${lista.imie} ${lista.nazwisko}
									
										<br/> <strong>Nr telefonu:</strong> ${lista.nrTel}
										<br/>
										<c:if test="${lista.idSpec eq '1'}"> 
											opłaty
										</c:if>
										<c:if test="${lista.idSpec eq '2'}"> 
											organizacja
										</c:if>
										<c:if test="${lista.idSpec eq '3'}"> 
											zwrot wycieczki
										</c:if>
									</p>
								</c:forEach>
							</c:if>
						</c:forEach>
						
						 <strong>Nazwa hotelu:</strong> ${entry.value.nazwaHotelu}<br>
						 <strong>Lokalizacja:</strong> ${entry.value.lokalizacja}<br/>
						<strong>Wyżywienie:</strong> ${entry.value.wyzywienie}<br/>
						<strong>Data wyjazdu:</strong> ${entry.value.dataWyjazdu}<br/>
						<strong>Data powrotu:</strong> ${entry.value.dataPowrotu}<br/>
						
					
					
					</div>
					
					
					<div class="right">
	
					<div class="zdj">
						<img src="${entry.value.zdj}" width="" height="" />
					</div>
	
			</div>
			
			<div style="clear:both;"></div>
					
						
					
				</div>
			</c:forEach>
			
		
			

		</div>
		

	</div>

<div id="footer" style="clear:both; padding-top:20px;">

        <p style="text-align:center;">Wykonał <strong>Łukasz Woś</strong> &copy; Wszelkie prawa zastrzeżone</p>

      	</div>

</body>
</html>