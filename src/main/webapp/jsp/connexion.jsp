<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bruush/css/style.css">
    <link rel="stylesheet" type="text/css" href="/bruush/css/connexion.css">
    <title>Page de connexion</title>
</head>
<body>
    <h1 class="site-name">BRUUSH</h1>
    <div class="form-rectangle form-connexion">
        <h2 style="text-align: center;">Connexion</h2>
        <c:if test="${ !empty requestScope.error }">
            <p class="bruush-form-error">Adresse email ou mot de passe incorrect.</p>
        </c:if>
        <form action="/bruush/action?id=connexion" method="post" class="form-example">
            <p class="form-label">Adresse email :</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="email" name="email" placeholder="Email" required>
            </div>

            <p class="form-label">Mot de passe :</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="password" name="mdp" placeholder="Mot de passe" required>
            </div>

            <div style="text-align: center;">
                <input class="form-submit-button" type="submit" value="Se connecter">
            </div>
        </form>
        <div class="link-wrapper">
            <a href="/bruush/jsp/create_account.jsp">Créer un compte</a>
        </div>
    </div>
</body>
</html>