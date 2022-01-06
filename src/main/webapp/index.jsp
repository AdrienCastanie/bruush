<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bruush</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/index.css">
    <script type="text/javascript" src="js/index.js"></script>
    <!-- ALERTIFY -->
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
</head>
<body>
<jsp:include page="jsp/header.jsp"/>
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
                    <label class="article-stock">Stock : <span
                            class="article-stock-value">${article.stock}</span></label>
                </div>
                <c:if test="${ not empty sessionScope.id }">
                    <div class="qty-article-container">
                        <input type="button" value="-" class="button-stock minus">
                        <div class="qty-article-selected">1</div>
                        <input type="button" value="+" class="button-stock plus">
                    </div>
                    <button id="${article.id}" class="stripe btnAcheter" style="visibility: hidden">Acheter</button>
                </c:if>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>