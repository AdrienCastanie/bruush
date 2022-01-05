<%--
  Created by IntelliJ IDEA.
  User: quent
  Date: 13/12/2021
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" href="/bruush/css/style.css">
    <link rel="stylesheet" href="/bruush/css/profile.css">
    <script type="text/javascript" src="/bruush/js/admin.js"></script>
</head>
<body>
<jsp:include page="profile-nav-bar.jsp"/>
<div class="profile-container">
    <h3>Gestion des produits</h3>
    <div>
        <table class="fl-table">
            <thead>
            <tr>
                <th>Nom</th>
                <th>Prix</th>
                <th>Stock</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articles}" var="article">
            <tr>
                <td>${article.nom}</td>
                <td>${article.prix}</td>
                <td>${article.stock}</td>
                <td>${article.description}</td>
                <td>
                    <button id="btn-delete-articles" onclick="onDeleteArticle(${article.id})">Supprimer</button>
                </td>
            </tr>
            </c:forEach>
            <tbody>
        </table>
    </div>
</div>
</body>
<script>
    document.getElementById("menu-item-admin-product").className += " menu-item-actif";
</script>
</html>

