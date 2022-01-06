<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/cart.css">
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="main-container" style="visibility: hidden">
    <div class="center-container cart-container">
        <h2>Panier</h2>
        <c:forEach items="${articles}" var="article">
            <div class="cart-article-container" id="${article.id}">
                <img src="${article.img}">
                <label class="article-title">${article.nom}</label>
                <div class="right-info-cart-article">
                    <label class="cart-article-price" data-price="${article.prix}">${article.prix}€</label>
                    <div class="qty-article-container">
                        <input type="button" value="-" class="button-stock minus">
                        <div id="qty-article" class="qty-article-selected">1</div>
                        <input type="button" value="+" class="button-stock plus">
                    </div>
                    <!-- <label class="subtotal-label">Sous total : 200€</label> -->
                </div>
            </div>
        </c:forEach>
        <span></span>

        <div class="bottom-cart-container">
            <div class="bottom-cart">
                <label class="total-label">Total : <span id="total-price">0</span>€</label>
                <button class="stripe" id="button-buy">Acheter</button>
            </div>
        </div>
    </div>
    <div id="modal-facture" class="modal" style="display: none">
        <div class="modal-content">
            <h1>Facture</h1>
            <c:forEach items="${articles}" var="article">
                <div class="facture-element" data-id="${article.id}">
                    <span class="facture-article-title">${article.nom}</span>
                    <span class="facture-article-price">${article.prix}€</span>
                    <span class="facture-article-qte">1</span>
                </div>
            </c:forEach>
            <div class="total-label">Total : <span id="facture-total-price">0</span>€</div>
            <button id="facture-submit">OK</button>
        </div>
    </div>
</div>
</body>
</html>
