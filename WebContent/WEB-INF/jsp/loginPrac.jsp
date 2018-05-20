<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="img/samolocik.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Logowanie Pracownika</title>

<link rel="stylesheet" href="css/styleInput.css" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Courgette|Roboto:400,700,900,900i&amp;subset=latin-ext" rel="stylesheet">


</head>
<body>

<c:url value="/index" var="strG" scope="page" />
	<a href="${strG}">
		<div id="logo" style="width: 620px;">
		
			<h1 > <span style="color:#FFC005; font-weight:bold;">  Dream </span><img src="img/samolocik.png" height="70px"width="70px"> Travel <span style="font-size:20px; color:#212121; font-family: 'Roboto', sans-serif;">Edit</span></h1>

		</div>
		</a>
	
	
	<div id="container">

		<form method="POST" action="pracownicy">
				
				<div style="color: red; margin: auto; padding: 10px; font-family: 'Roboto', sans-serif;">${errorString}</div>
					
					<input type="text" placeholder="login" name="login" onfocus="this.placeholder=''" onblur="this.placeholder='login'" />
				
				
					<input type="password" placeholder="hasło" name="haslo" onfocus="this.placeholder=''" onblur="this.placeholder='hasło'" />
				
						<input type="submit" value="Zaloguj się" />
						<a href="${pageContext.request.contextPath}"><div class="powrot">Powrót</div></a>
						
					
		</form>
	</div>

<!-- 

	<p style="color: red;">${errorString}</p>
	
	<form method="POST" action="pracownicy">
		<table border="0">
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login" value="${login}"/></td>
			</tr>
			<tr>
				<td>Haslo:</td>
				<td><input type="password" name="haslo" /></td>
			</tr>
			<tr>
				<td>
					<input colspan="2" type="submit" value="Submit" />
					<a href="${pageContext.request.contextPath}">Powrot</a>
				</td>
			</tr>
		
		</table>
	</form>
 -->

</body>
</html>