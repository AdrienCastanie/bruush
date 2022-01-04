<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" src="js/quantity.js"></script>
<div class="main-container">
    <c:forEach items="${articles}" var="article">
        <div class="center-container article-container">
            <img class="article-img" src="${article.img}">
            <div class="title-description-container">
                <label class="article-title">${article.nom}</label>
                <p>${article.description}</p>
            </div>
            <div class="right-article-container">
                <div class="price-stock-container">
                    <label class="article-price">${article.prix}€</label>
                    <label id="stock" class="article-stock">Stock : ${article.stock}</label>
                </div>
                <div class="qty-article-container">
                    <input type="button" value="-" class="button-stock" onclick="decrementValue()">
                    <div id="qty-article" class="qty-article-selected">1</div>
                    <input type="button" value="+" class="button-stock"  onclick="incrementValue()">
                </div>
                <button id="${article.id}" class="stripe btnAcheter" style="visibility: hidden">Acheter</button>
            </div>
        </div>
    </c:forEach>
</div>
</body>
<script>
    window.addEventListener('load', () => {
        Array.from(document.querySelectorAll(".btnAcheter")).forEach(elem => {
            elem.style.visibility = "visible"
            elem.addEventListener('click', (event) => {
                const obj = JSON.parse(localStorage.getItem('panier')) || {};
                obj[elem.id] = event.target.parentElement.querySelector('.qty-article-selected').textContent || "1";
                localStorage.setItem('panier', JSON.stringify(obj));
            })
        })
    });
</script>
</html>