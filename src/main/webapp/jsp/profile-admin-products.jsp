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
    <div class="add-article-container">
        <form action="/bruush/action?id=admin_products" method="post" class="form-example">
            <p class="form-label">Nom :</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="text" name="nom" placeholder="nom" required>
            </div>

            <p class="form-label">Description</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="text" name="description" placeholder="Description" required>
            </div>

            <p class="form-label">Image</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="url" name="image" placeholder="url de l'image" required>
            </div>

            <p class="form-label">Prix</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="number" name="prix" placeholder="10" required>
            </div>

            <p class="form-label">Stock</p>
            <div class="form-input-wrapper">
                <input class="form-input" type="number" name="stock" placeholder="100" required>
            </div>

            <div style="text-align: center;">
                <input class="form-submit-button" type="submit" value="Ajouter">
            </div>
        </form>
    </div>
    <div>
        <table class="fl-table">
            <thead>
            <tr>
                <th>Nom</th>
                <th>Prix</th>
                <th>Description</th>
                <th>Stock</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${articles}" var="article">
            <tr>
                <td>${article.nom}</td>
                <td>${article.prix}</td>
                <td>${article.description}</td>
                <td>
                    <input type="text" name="stock" id="stock-${article.id}" value="${article.stock}">
                    <button id="save-new-qte-articles" onclick="onQteChange(${article.id})">Enregistrer</button>
                </td>
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

