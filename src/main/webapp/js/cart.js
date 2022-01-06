window.addEventListener('load', () => {
    const totalPriceElem = document.getElementById('total-price');
    const totalPriceElemFacture = document.getElementById('facture-total-price');
    const panier = JSON.parse(localStorage.getItem("panier")) || {};
    let price = 0;
    [].slice.call(document.getElementsByClassName('cart-article-container')).map(elem => {
        if (!(elem.id in panier)) {
            elem.remove()
        } else {
            const qte = elem.getElementsByClassName('qty-article-selected')[0];
            qte.innerText = panier[elem.id]
            const articlePrice = Number.parseInt(
                elem.getElementsByClassName('cart-article-price')[0].getAttribute('data-price'));
            price += articlePrice * panier[elem.id];
            elem.getElementsByClassName('button-stock minus')[0].addEventListener('click', () => {
                if (Number.parseInt(qte.innerText) > 1) {
                    qte.innerText = Number.parseInt(qte.innerText) - 1;
                    totalPriceElem.innerText = Number.parseInt(totalPriceElem.innerText) - articlePrice;
                    totalPriceElemFacture.innerText = totalPriceElem.innerText;
                    panier[elem.id] = Number.parseInt(qte.innerText)
                    localStorage.setItem("panier", JSON.stringify(panier))
                }
            });
            elem.getElementsByClassName('button-stock plus')[0].addEventListener('click', () => {
                qte.innerText = Number.parseInt(qte.innerText) + 1;
                totalPriceElem.innerText = Number.parseInt(totalPriceElem.innerText) + articlePrice;
                totalPriceElemFacture.innerText = totalPriceElem.innerText;
                panier[elem.id] = Number.parseInt(qte.innerText)
                localStorage.setItem("panier", JSON.stringify(panier))
            });
        }
    });
    document.getElementsByClassName('main-container')[0].style.visibility = "visible"
    document.getElementById('button-buy').addEventListener('click', async () => {
        const data = new URLSearchParams();
        for (let item in panier) {
            data.append('panier', item + '-' + panier[item]);
        }
        data.append('total', totalPriceElem.innerText)
        const response = await fetch(window.location.origin + "/bruush/action?id=buy", {
            method: 'POST',
            body: data
        });
        [].slice.call(document.getElementsByClassName('facture-element')).map(elem => {
            const id = elem.getAttribute('data-id');
            if (!(id in panier)) {
                elem.remove()
            } else {
                elem.getElementsByClassName('facture-article-qte')[0].innerText = panier[id];
            }
        });
        localStorage.setItem("panier", JSON.stringify({}));
        document.getElementById("modal-facture").style.display = "block";
        document.getElementById('facture-submit').addEventListener('click', () => {
            window.location.href = window.location.origin + "/bruush";
        })
    })
    totalPriceElem.innerText = price;
    totalPriceElemFacture.innerText = price;
});