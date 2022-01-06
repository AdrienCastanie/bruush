<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="/bruush/css/style.css">
    <link rel="stylesheet" href="/bruush/css/profile.css">
</head>
<body>
<jsp:include page="profile-nav-bar.jsp"/>
<div class="profile-container">
    <h3>Historique des commandes</h3>
    <div class="commandes">
        <c:forEach var="entry" items="${historiqueArticle}">
            <h5>Commande du <span>${entry.key.date}</span></h5>
            <table class="fl-table">
                <thead>
                <tr>
                    <th>Nom</th>
                    <th>Quantité</th>
                    <th>Prix Unit</th>
                    <th>Total</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="articles" items="${entry.value}" varStatus="loop">
                <tr>
                    <td>${articles.nom}</td>
                    <td>${historiqueAchat[entry.key][loop.index].qte}€</td>
                    <td>${articles.prix}€</td>
                    <td><c:out value="${articles.prix * historiqueAchat[entry.key][loop.index].qte}€"/></td>
                </tr>
                </c:forEach>
                <tbody>
            </table>
            <span>Total: <span>${entry.key.total}</span>€</span>
        </c:forEach>
    </div>
</div>
</body>
<script>
    document.getElementById("menu-item-command-history").className += " menu-item-actif";
</script>
</html>
