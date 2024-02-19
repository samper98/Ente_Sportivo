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
<title>Iscritti </title>
</head>
	</div>
	<caption>
		<h2>Lista Partecipanti </h2>
	</caption>
	<div align="center">
		<table class="table" border="1" cellpadding="5">
		
          <c:set var="idgara" value='${requestScope["id-gara"]}' />    
	     
			<tr>
				<th>Nominativo</th>
				<th>Eta</th>
			    <th>Tempo</th>	
			</tr>
			<c:forEach var="VelocistaPartecipantiGara" items="${listaPartecipanti}">
					<tr>
						<td><c:out value="${VelocistaPartecipantiGara.nominativo}" /></td>
						<td><c:out value="${VelocistaPartecipantiGara.eta}" /></td>
						<td><c:out value="${VelocistaPartecipantiGara.tempo}" /></td>
						
					</tr>
			</c:forEach>
			
		</table>
				<td><a class="btn btn-success"  style="background-color:orange;" href=/ente-sportivo/homepage-staff-gara />  TORNA ALLA HOME  </a></td>
		
	</div>
