<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/connexion.css">
    <title>Page de création de compte</title>
</head>
<body>
    <h1 class="site-name">BRUUSH</h1>
    <div class="form-rectangle form-connexion">
        <h2 style="text-align: center;">Création de compte</h2>
        <form action="/bruush/action?id=create_account" method="post" class="form-example">
            <p class="form-label">Nom :</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="text" name="nom" placeholder="Nom" required>
            </div>

            <p class="form-label">Prénom :</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="text" name="prenom" placeholder="Prenom" required>
            </div>

            <p class="form-label">Adresse email :</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="email" name="email" placeholder="Email" required>
            </div>

            <p class="form-label">Mot de passe :</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="password" name="mdp" placeholder="Mot de passe" required>
            </div>

            <div style="text-align: center;">
                <input class="form-submit-button" type="submit" value="Créer son compte">
            </div>
        </form>
        <div class="link-wrapper">
            <a href="connexion.jsp">Déjà un compte</a>
        </div>
    </div>
</body>
</html>