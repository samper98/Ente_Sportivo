<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Lista Gare</title>
    </head>
    <body>
        <div align="center">
            <table border="1" cellpadding="5">
                <caption><h2>Lista Gare</h2></caption>
                <tr>
                    <th>ID</th>
                    <th>Luogo</th>
                    <th>Data e Ora</th>
                    
                </tr>
                <c:forEach var="gara" items="${listaGare}">
                    <tr>
                        <td><c:out value="${gara.idGara}" /></td>
                        <td><c:out value="${gara.luogo}" /></td>
                        <td><c:out value="${gara.dataGara}" /></td>
                    </tr>
                </c:forEach>
            </table>
        </div>	
    </body>
</html>