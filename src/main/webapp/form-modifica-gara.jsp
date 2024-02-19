<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Ente Sportivo</title>
			<link rel="icon" href="img/icona.png" type="image/png">
		<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link th:rel="stylesheet" href="/css/main.css">
	</head>

	<body>
	
        <center>
            <h1> Modifica Dati Gara </h1>
        </center>
		
		<form action="/ente-sportivo/modifica-gara" method="post">
		
			
			
			<label for="luogo">Luogo</label>
			<br>
			<input type="text" id="luogo" name="luogo" value=""   />
       
            <label for="data"></label>
			<br>Data e Ora <br>
			<input type="datetime-local" id="data" name="data-gara" value=""   />
        
			 <c:set var="idgara" value='${requestScope["id-gara"]}' />    
	        
		     <input type="hidden" id="id-gara"	name="id-gara" value="<c:out value="${idgara}" />"  />
		     
			<input type="submit" value="modifica" style="color: orange;">
			
		</form>
  
	</body>
</html>