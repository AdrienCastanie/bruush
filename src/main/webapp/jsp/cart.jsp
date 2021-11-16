<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/cart.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="main-container">
    <div class="center-container cart-container">
        <h2>Panier</h2>
        <div class="cart-article-container">
            <img src="/bruush/img/article1.jpg">
            <label class="article-title">Chiotte 3000</label>
            <div class="right-info-cart-article">
                <label class="cart-article-price">200€</label>
                <label class="subtotal-label">Sous total : 200€</label>
            </div>
        </div>
        <span></span>

        <div class="bottom-cart-container">
            <div class="bottom-cart">
                <label class="total-label">Total : 400€</label>
                <button class="stripe">Acheter</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
