<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<title>Ente Sportivo</title>
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
            <h1> Iscrizione Gara </h1>
        </center>
		
		<form action="/ente-sportivo/iscrizione" method="post">
		
			<p>Per favore, indicare i dati per svolgere la gara  !!!.</p>
			
			<label for="codice_fiscale">Codice Fiscale</label>
			<br>
			<input type="text" id="codice_fiscale" name="codice_fiscale" minlength="16" maxlength="16" value=""   />
	        <c:set var="idgara" value='${requestScope["id-gara"]}' />    
	        
		     <input type="hidden" id="id-gara"	name="id-gara" value="<c:out value="${idgara}" />"  />
       
      
        
            
			
			<input type="submit" value="iscriviti">
			
		</form>
  
	</body>
</html>