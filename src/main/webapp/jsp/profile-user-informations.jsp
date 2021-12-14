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
    <h3>Informations personnel</h3>
    <div>
        <div class="personnal-info-input-container">
            <form action="/bruush/action?id=personal-info" method="post">
                <div class="name">
                    <label class="input-label" for="name">Nom :</label>
                    <input type="text" name="name" id="name" value="<%= session.getAttribute("nom") %>">
                </div>
                <div class="surname">
                    <label class="input-label" for="surname">Prénom :</label>
                    <input type="text" name="surname" id="surname" value="<%= session.getAttribute("prenom") %>">
                </div>
                <div class="address">
                    <label class="input-label" for="address">Adresse :</label>
                    <input type="text" name="address" id="address" value="<%= session.getAttribute("adresse") %>">
                </div>
                <input class="stripe" type="submit" value="Enregistrer">
            </form>
        </div>
    </div>
</div>
</body>
<script>
    document.getElementById("menu-item-personal-info").className += " menu-item-actif";
</script>
</html>
