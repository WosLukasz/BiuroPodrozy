<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="icon" href="img/samolocik.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja</title>

<link rel="stylesheet" href="css/styleInput.css" type="text/css" />
<link href="https://fonts.googleapis.com/css?family=Courgette|Roboto:400,700,900,900i&amp;subset=latin-ext" rel="stylesheet">


</head>
<body>

	<c:url value="/index" var="strG" scope="page" />
	<a href="${strG}">
		<div id="logo">
		
			<h1 > <span style="color:#FFC005; font-weight:bold;">  Dream </span><img src="img/samolocik.png" height="70px"width="70px"> Travel</h1>

		</div>
		</a>
		
		
		<div id="container" style="width:320px;">

		<form title="Rejestracja" method="POST" action="rejestracja" class="mytable">
				
				<div style="color: red; margin: auto; padding: 10px; font-family: 'Roboto', sans-serif;">${errorString}</div>
					
					
					<input type="text" placeholder="imię" name="imie" onfocus="this.placeholder=''" onblur="this.placeholder='imię'" /> <font color="red">*</font>
					
					<input type="text" placeholder="nazwisko"  name="nazwisko" onfocus="this.placeholder=''" onblur="this.placeholder='nazwisko'" /> <font color="red">*</font>
					
					<input type="text" placeholder="nr. dowodu" name="dowod" onfocus="this.placeholder=''" onblur="this.placeholder='nr. dowodu'" /> <font color="red">*</font>
					
					<input type="text" placeholder="login" name="login" onfocus="this.placeholder=''" onblur="this.placeholder='login'" /> <font color="red">*</font>
				
				
					<input type="password" placeholder="hasło" name="haslo" onfocus="this.placeholder=''" onblur="this.placeholder='hasło'" /> <font color="red">*</font>
				
						<input type="submit" value="Zarejestruj się" />
						<a href="${pageContext.request.contextPath}"><div class="powrot">Powrót</div></a>
						
						
						<div style=" margin: auto; padding: 10px; font-family: 'Roboto', sans-serif;"><font color="red">*</font> - pola wymagane <br/>
						<p>Aby korzystać z portalu musisz być zarejestrowany w bazie jako użytkownik</p></div>
						
						
					
		</form>
	</div>
		
<!--  

	<p class="errorRed">${errorString}</p>

	<p>Aby korzystać z portalu musisz być zarejestrowany w
		bazie jako użytkownik</p>

	<form title="Rejestracja" method="POST" action="rejestracja" class="mytable">

		

		<table border="0" align="center" cellpadding="4">
			<tr>
				<td>Imię</td>
				<td><input type="text" name="imie"  /><font
					color="red">*</font></td>
			</tr>
			<tr>
				<td>Nazwisko</td>
				<td><input type="text" name="nazwisko" /> <font color="red">*</font></td>
			</tr>
			<tr>
				<td>Nr dowodu:</td>
				<td><input type="text" name="dowod" /><font color="red">*</font></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td><input type="text" name="login" /><font color="red">*</font></td>
			</tr>
			<tr>
				<td>Haslo:</td>
				<td><input type="password" name="haslo" /><font color="red">*</font></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Zarejestruj" class="formButton"/> &nbsp;<a
					href="${pageContext.request.contextPath}/">Powrót</a></td>
			</tr>
		</table>
	</form>
	<p>
		<font color="red">*</font> - pola wymagane
	</p>

-->

</body>
</html>