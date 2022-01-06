<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="/bruush/css/profile-nav-bar.css">
<div class="profile-nav-bar-container">
    <a class="decoration-none"
       href="/bruush/">
        <h1>BRUUSH</h1>
    </a>
    <span></span>
    <a id="menu-item-personal-info"
       class="menu-item"
       href="/bruush/action?id=personal-info">
        <h2>Information personnel</h2>
    </a>
    <a id="menu-item-command-history"
       class="menu-item"
       href="/bruush/action?id=history">
        <h2>Historique de commande</h2>
    </a>
    <c:if test="${ sessionScope.admin == 1 }">
        <a id="menu-item-admin-users"
           class="menu-item"
           href="/bruush/action?id=admin_clients">
            <h2>Utilisateurs</h2>
        </a>
        <a id="menu-item-admin-product"
           class="menu-item"
           href="/bruush/action?id=admin_products">
            <h2>Produits</h2>
        </a>
    </c:if>
</div>