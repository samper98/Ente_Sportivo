<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<html>
<head>
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

<title>Home page Staff-Gara</title>
</head>

<body></body>

	<div class="conteiner" align="right">
		<img src="img/staff2.jpg" width="500" height="200" align="right">
		<img src="img/staff.jpg" width="465" height="200" align="center">
		<img src="img/staff3.jpg" width="500" height="200" align="left">

	</div>
	   <br>
	   <h1><Strong style="color:orange">&#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 
	    &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160 &#160   STAFF GARA</Strong> </h1>
	<br>
<a class="btn btn-success " style="background-color:orange;" href="/ente-sportivo/form-gara">  
			Nuova Gara  </a>

	<div class="conteiner" align="left">
		Ordina per Luogo  
		<a class="btn btn-success" style="background-color:red;"
			href="/ente-sportivo/homepage-staff-gara?ordinamento=asc"> 
		</a> 
		 &#160 &#160 &#160 Ordina per ID <a class="btn btn-success " style="background-color: #ffffcc;"
			href="/ente-sportivo/homepage-staff-gara"> </a>
		&#160 &#160 &#160 Ordina per Data
		 <a class="btn btn-success " style="background-color: lime;"
			href="/ente-sportivo/homepage-staff-gara?ordinamento=data"> </a>
	</div>
	<caption>
		<h2>Lista Gare Svolte</h2>
	</caption>
	<div align="center">
		<table class="table" border="1" cellpadding="5">

			<tr>
				<th>ID</th>
				<th>Luogo</th>
				<th>Data e Ora</th>
				<th>Visualizza Dettagli</th>
			</tr>

			<c:set var="today" value="<%=LocalDateTime.now()%>" />
			<c:forEach var="gara" items="${listaGare}">
				<c:if test="${gara.dataGara.isBefore(today)}">
					<tr>
						<td><c:out value="${gara.idGara}" /></td>
						<td><c:out value="${gara.luogo}" /></td>
						<td><c:out value="${gara.dataGara}" /></td>
						<td><a class="btn btn-success" style="background-color:orange;"
							href="/ente-sportivo/visualizza-dettaglio-staff?id=
							<c:out value="${gara.idGara}" />">Visualizza
								Dettaglio</a></td>
								
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>

	<br>
	<caption>
	
		<h2>Lista Gare Da Svolgere</h2>
	</caption>
	<div class="conteiner" align="center">
		<table class="table " border="1" cellpadding="5">
			<tr>
				<th>ID</th>
				<th>Luogo</th>
				<th>Data e Ora</th>
				<th>Iscritti</th>
				<th>&#160 &#160 &#160 &#160 &#160 &#160 &#160     Azioni</th>
			</tr>

			<c:set var="today" value="<%=LocalDateTime.now()%>" />
			<c:forEach var="gara" items="${listaGare}">
				<c:if
					test="${gara.dataGara.isEqual(today) || gara.dataGara.isAfter(today)}">
					<tr>
						<td><c:out value="${gara.idGara}" /></td>
						<td><c:out value="${gara.luogo}" /></td>
						<td><c:out value="${gara.dataGara}" /></td>
						<td><a class="btn btn-success" style="background-color:orange;"
							href="/ente-sportivo/visualizza-iscritti-staff?id=
						<c:out value="${gara.idGara}" />">
								Visualizza Iscritti</a></td>
								<td><a class="btn btn-success" style="background-color:orange;"
								href="/ente-sportivo/form-modifica-gara?id-gara=
							<c:out value='${gara.idGara}' />" >Modifica 
								</a>
									<a class="btn btn-success" style="background-color:orange;" onclick="confermaEliminazione(event)"
							href="/ente-sportivo/elimina-gara?id=<c:out value='${gara.idGara}' />" >Elimina 
								</a></td>
					

					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
</head>

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
	integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
	crossorigin="anonymous"></script>
	
	<script>
	function confermaEliminazione(event) {
		  // Mostra la finestra di dialogo di conferma
		  var conferma = confirm("Sei sicuro?");
		  // Se l'utente conferma, l'eliminazione viene eseguita
		  if (!conferma) {
		    // Se l'utente annulla, impedisci l'esecuzione dell'azione predefinita del link
		    event.preventDefault(); // Questo impedisce al link di eseguire l'azione predefinita (navigare all'URL)
		  }
		}
</script>

</body>
</html>