<%-- 
    Document   : Liste
    Created on : 24 juin 2024, 13:05:54
    Author     : ROYAL COMPUTER
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des employés</title>
</head>
<body>
    <h2>Liste des employés</h2>
    <table border="1">
        <thead>
            <tr>
                <th>ID Employé</th>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Téléphone</th>
            </tr>
        </thead>
        <tbody>
            <!-- Boucle sur la liste des employés -->
            <c:forEach var="employe" items="${employes}">
                <tr>
                    <td>${employe.id}</td>
                    <td>${employe.nom}</td>
                    <td>${employe.prenom}</td>
                    <td>${employe.telephone}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
