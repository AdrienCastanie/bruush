function incrementValue() {
    const stock = document.getElementById("stock").innerText.split(' ')[2];
    let qty = parseInt(document.getElementById("qty-article").innerText);
    if (qty < stock) {
        qty++;
        document.getElementById("qty-article").innerText = qty.toString();
    }
}

function decrementValue() {
    let qty = parseInt(document.getElementById("qty-article").innerText);
    if (qty > 1) {
        qty--;
        document.getElementById("qty-article").innerText = qty.toString();
    }
}