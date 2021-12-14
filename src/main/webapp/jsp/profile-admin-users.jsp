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
                <th>Email</th>
                <th>Bloquer</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>Pineau</td>
                <td>Quentin</td>
                <td>test@test.fr</td>
                <td>
                    <label class="switch">
                        <input type="checkbox" checked>
                        <span class="slider round"></span>
                    </label>
                </td>
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
