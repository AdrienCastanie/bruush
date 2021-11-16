<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bruush</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<jsp:include page="jsp/header.jsp"/>
<div class="main-container">
    <div class="center-container article-container">
        <img src="img/article1.jpg">
        <div class="title-description-container">
            <label class="article-title">Chiotte 3000</label>
            <p>
                Use this HTML text code generator and you'll never have to tell another client exactly what Ipsum Lorem
                text is because this tool generates dummy content for your mock-ups in readable English.
                All in all it's a very easy to use online tool for generating HTML text.
            </p>
        </div>
        <div class="right-article-container">
            <div class="price-stock-container">
                <label class="article-price">200€</label>
                <label class="article-stock">Stock : 35</label>
            </div>
            <button class="stripe">Acheter</button>
        </div>
    </div>
</div>
</body>
</html>