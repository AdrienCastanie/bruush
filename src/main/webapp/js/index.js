window.addEventListener('load', () => {
    [].slice.call(document.getElementsByClassName('article-container')).map(elem => {
        elem.getElementsByClassName('button-stock minus')[0].addEventListener('click', () => {
            const quantityElem = elem.getElementsByClassName("qty-article-selected")[0];
            let qty = parseInt(quantityElem.innerText);
            if (qty > 1) {
                qty--;
                quantityElem.innerText = qty.toString();
            }
        });
        elem.getElementsByClassName('button-stock plus')[0].addEventListener('click', () => {
            const stock = elem.getElementsByClassName("article-stock-value")[0].innerText;
            const quantityElem = elem.getElementsByClassName("qty-article-selected")[0];
            let qty = parseInt(quantityElem.innerText);
            if (qty < parseInt(stock)) {
                qty++;
                quantityElem.innerText = qty.toString();
            }
        });
    });

    Array.from(document.querySelectorAll(".btnAcheter")).forEach(elem => {
        elem.style.visibility = "visible"
        elem.addEventListener('click', (event) => {
            const obj = JSON.parse(localStorage.getItem('panier')) || {};
            obj[elem.id] = Number.parseInt(event.target.parentElement.querySelector('.qty-article-selected')
                .textContent) || 1;
            localStorage.setItem('panier', JSON.stringify(obj));
        })
    })
});