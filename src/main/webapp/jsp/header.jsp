<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<header>
    <a href="/bruush/"><h1>BRUUSH</h1></a>
    <div class="header-user-container">
        <c:if test="${ !empty sessionScope.id }">
            <div class="my-account-dropdown">
                <a href="/bruush/action?id=personal-info"
                   class="header-clickable-connection header-clickable-item dropbtn">
                    <label class="user-name-label">${ sessionScope.prenom } ${ sessionScope.nom }</label>
                    <img src="img/avatar.png">
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

        <c:if test="${ (not empty sessionScope.bloque && sessionScope.bloque == 1) || not empty sessionScope.id }">
            <div id="btnCart">
                <div class="header-clickable-item">
                    <img src="/bruush/img/panier.png">
                </div>
                <form action="/bruush/action?id=cart" method="POST">
                    <input type="hidden" class="cart" name="cart">
                </form>
            </div>
        </c:if>
        <script>
            const btnCart = document.querySelector("#btnCart");
            btnCart.addEventListener('click', event => {
                const form = btnCart.querySelector('form')
                form.querySelector('.cart').value = localStorage.getItem('panier') || "{}"
                form.submit()
            })
        </script>
    </div>
    <span></span>
</header>
