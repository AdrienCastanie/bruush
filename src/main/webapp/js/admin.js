function onBloqueChange(input, userId) {
    window.location.href = window.location.origin + "/bruush/action?id=admin_clients&id_client=" + userId + "&blocked=" + input.checked;
}

function onDeleteArticle(articleId) {
    window.location.href = window.location.origin + "/bruush/action?id=admin_products&id_article=" + articleId;
}

function onQteChange(articleId) {
    let newQte = document.getElementById("stock-" + articleId).value;
    window.location.href = window.location.origin + "/bruush/action?id=admin_products&id_article=" + articleId + "&new_qte=" + newQte;
}