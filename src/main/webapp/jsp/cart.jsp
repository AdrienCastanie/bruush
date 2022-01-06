<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="/bruush/css/style.css">
    <link rel="stylesheet" href="/bruush/css/cart.css">
    <script>
        window.addEventListener('load', () => {
            const totalPriceElem = document.getElementById('total-price');
            const panier = JSON.parse(localStorage.getItem("panier")) || {};
            let price = 0;
            [].slice.call(document.getElementsByClassName('cart-article-container')).map(elem => {
                if (!(elem.id in panier)) {
                    elem.remove()
                } else {
                    const qte = elem.getElementsByClassName('qty-article-selected')[0];
                    qte.innerText = panier[elem.id]
                    const articlePrice = Number.parseInt(
                        elem.getElementsByClassName('cart-article-price')[0].getAttribute('data-price'));
                    price += articlePrice * panier[elem.id];
                    elem.getElementsByClassName('button-stock minus')[0].addEventListener('click', () => {
                        if (Number.parseInt(qte.innerText) > 1) {
                            qte.innerText = Number.parseInt(qte.innerText) - 1;
                            totalPriceElem.innerText = Number.parseInt(totalPriceElem.innerText) - articlePrice;
                            panier[elem.id] = Number.parseInt(qte.innerText)
                            localStorage.setItem("panier", JSON.stringify(panier))
                        }
                    });
                    elem.getElementsByClassName('button-stock plus')[0].addEventListener('click', () => {
                        qte.innerText = Number.parseInt(qte.innerText) + 1;
                        totalPriceElem.innerText = Number.parseInt(totalPriceElem.innerText) + articlePrice;
                        panier[elem.id] = Number.parseInt(qte.innerText)
                        localStorage.setItem("panier", JSON.stringify(panier))
                    });
                }
            })
            document.getElementsByClassName('main-container')[0].style.visibility = "visible"
            document.getElementById('button-buy').addEventListener('click', () => {
                const data = new URLSearchParams();
                for (let item in panier) {
                    data.append('panier', item + '-' + panier[item]);
                    data.append('total', totalPriceElem.innerText)
                }
                fetch(window.location.origin + "/bruush/action?id=buy", {
                    method: 'POST',
                    body: data
                })
                window.location.href = window.location.origin + "/bruush"
            })
            totalPriceElem.innerText = price;
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
</div>
</body>
</html>
