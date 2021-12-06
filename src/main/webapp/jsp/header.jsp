<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <h1>BRUUSH</h1>
    <div class="header-user-container">
        <c:if test="${ !empty sessionScope.id }">
            <div class="my-account-dropdown">
                <a href="/bruush/jsp/profile-user-informations.jsp"
                   class="header-clickable-connection header-clickable-item dropbtn">
                    <label class="user-name-label">${ sessionScope.prenom } ${ sessionScope.nom }</label>
                    <img src="/bruush/img/avatar.png">
                </a>
                <div class="dropdown-content">
                    <a href="/bruush/action?id=disconnection">Déconnexion</a>
                </div>
            </div>
        </c:if>
        <c:if test="${ empty sessionScope.id }">
            <a href="/bruush/jsp/connexion.jsp" class="header-clickable-connection header-clickable-item">
                <label class="user-name-label">Se connecter</label>
                <img src="img/avatar.png">
            </a>
        </c:if>

        <a href="/bruush/jsp/cart.jsp">
            <div class="header-clickable-item">
                <img src="/bruush/img/panier.png">
            </div>
        </a>
    </div>
    <span></span>
</header>
