<%--
  Created by IntelliJ IDEA.
  User: quent
  Date: 16/11/2021
  Time: 16:35
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
    <h3>Gestion des utilisateurs</h3>
    <div>
        <table class="fl-table">
            <thead>
            <tr>
                <th>Nom</th>
                <th>Prénom</th>
                <th>Email</th>
                <th>Bloquer</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${clients}" var="client">
            <tr>
                <td>${client.nom}</td>
                <td>${client.prenom}</td>
                <td>${client.mail}</td>
                <td>
                    <label class="switch">
                        <c:if test="${ client.bloque == 1 }">
                            <input type="checkbox" checked onclick="onBloqueChange(this, ${client.id})">
                        </c:if>
                        <c:if test="${ client.bloque == 0 }">
                            <input type="checkbox" onclick="onBloqueChange(this, ${client.id})">
                        </c:if>
                        <span class="slider round"></span>
                    </label>
                </td>
            </tr>
            </c:forEach>
            <tbody>
        </table>
    </div>
</div>
</body>
<script>
    document.getElementById("menu-item-admin-users").className += " menu-item-actif";
</script>
</html>
