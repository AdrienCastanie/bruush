<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="/bruush/css/style.css">
    <link rel="stylesheet" href="/bruush/css/cart.css">
    <script>
        window.addEventListener('load', () => {
            const panier = JSON.parse(localStorage.getItem("panier")) || {};
            [].slice.call(document.getElementsByClassName('cart-article-container')).map(elem => {
                if (!(elem.id in panier)) {
                    elem.remove()
                }
            })
            document.getElementsByClassName('main-container')[0].style.visibility = "visible"
            document.getElementById('button-buy').addEventListener('click', () => {
                const data = new URLSearchParams();
                for (let item in panier) {
                    data.append('panier', item + '-' + panier[item])
                }
                fetch(window.location.origin + "/bruush/action?id=buy", {
                    method: 'POST',
                    body: data
                })
                window.location.href = window.location.origin + "/bruush"
            })
        })
    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<script type="text/javascript" src="/bruush/js/quantity.js"></script>
<div class="main-container" style="visibility: hidden">
    <div class="center-container cart-container">
        <h2>Panier</h2>
        <c:forEach items="${articles}" var="article">
            <div class="cart-article-container" id="${article.id}">
                <img src="${article.img}">
                <label class="article-title">${article.nom}</label>
                <div class="right-info-cart-article">
                    <label class="cart-article-price">${article.prix}€</label>
                    <div class="qty-article-container">
                        <input type="button" value="-" class="button-stock" onclick="decrementValue()">
                        <div id="qty-article" class="qty-article-selected">1</div>
                        <input type="button" value="+" class="button-stock"  onclick="incrementValue()">
                    </div>
                    <label class="subtotal-label">Sous total : 200€</label>
                </div>
            </div>
        </c:forEach>
        <span></span>

        <div class="bottom-cart-container">
            <div class="bottom-cart">
                <label class="total-label">Total : 400€</label>
                <button class="stripe" id="button-buy">Acheter</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
