function onBloqueChange(input, userId) {
    window.location.href = window.location.origin + "/bruush/action?id=admin_clients&id_client=" + userId + "&blocked=" + input.checked;
}