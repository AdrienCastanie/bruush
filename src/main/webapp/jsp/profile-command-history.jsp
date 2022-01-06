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
            <c:forEach var="articles" items="${entry.value}" varStatus="loop">
                <span>Nom: <span>${articles.nom}</span></span>
                <span>Quantité: <span>${historiqueAchat[entry.key][loop.index].qte}</span></span>
                <br/>
            </c:forEach>
        </c:forEach>
    </div>
</div>
</body>
<script>
    document.getElementById("menu-item-command-history").className += " menu-item-actif";
</script>
</html>
