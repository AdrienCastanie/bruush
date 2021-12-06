<%--
  Created by IntelliJ IDEA.
  User: quent
  Date: 06/12/2021
  Time: 10:17
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
    <h3>Historique des commandes</h3>
</div>
</body>
<script>
    document.getElementById("menu-item-command-history").className += " menu-item-actif";
</script>
</html>
