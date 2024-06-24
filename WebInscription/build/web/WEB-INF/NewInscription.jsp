<%-- 
    Document   : NewInscription
    Created on : 22 juin 2024, 07:24:14
    Author     : ROYAL COMPUTER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>InscriptionOrace</title>
    </head>
    
    <body>
        <h2>Ajouter un nouvel employé</h2>
    <form action="InscriptionServlet" method="post">
        
        <label>ID Employé:</label><br>
        <input type="number" name="id_employe" required><br>
        
        <label>Nom:</label><br>
        <input type="text" name="nom" required><br>
        
        <label>Prénom:</label><br>
        <input type="text" name="prenom" required><br>
        
        <label>Téléphone:</label><br>
        <input type="text" name="telephone" required><br><br>
        
        <input type="submit" value="Ajouter">
        
    </form>
    </body>
</html>
