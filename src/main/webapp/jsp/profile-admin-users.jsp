<%--
  Created by IntelliJ IDEA.
  User: quent
  Date: 16/11/2021
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profil</title>
    <link rel="stylesheet" href="/bruush/css/style.css">
    <link rel="stylesheet" href="/bruush/css/profile.css">
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
                <th>Bloqu</th>
                <th>Dernière modification</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>S5</td>
                <td>Ma première note</td>
                <td>09/09/2021</td>
                <td>10/09/2021</td>
            </tr>
            <tbody>
        </table>
    </div>
</div>
</body>
<script>
    document.getElementById("menu-item-admin-users").className += " menu-item-actif";
</script>
</html>
