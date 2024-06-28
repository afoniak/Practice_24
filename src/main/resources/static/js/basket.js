function addToCart(productId, title, price, img, text) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let product = cart.find(p => p.id === productId);

    if (product) {
        product.quantity += 1;
    } else {
        cart.push({ id: productId, title: title, price: price, img: img, text: text, quantity: 1 });
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartUI();
}

function removeFromCart(id) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let index = cart.findIndex(item => item.id === id);

    if (index > -1) {
        cart.splice(index, 1);
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    updateCartUI();
}

function updateCartUI() {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let total = cart.reduce((acc, curr) => acc + curr.price * curr.quantity, 0);
    let cartHtml = cart.map(item => `
            <div class="card">
                <img src="${item.img}" alt="Product Image" class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${item.title}</h5>
                    <p class="card-text">${item.text}</p>
                    <p class="card-price">Цена: ₴${item.price}</p>
                    <p>Количество: ${item.quantity}</p>
                    <button class="btn btn-danger" onclick="removeFromCart(${item.id})">Удалить из корзины</button>
                </div>
            </div>
        `).join('');

    document.querySelector('.container-goods-card').innerHTML = cartHtml;
    document.querySelector('.container-category .text-center h1').innerHTML = `До сплати <strong>₴${total.toFixed(2)}</strong>`;
}

document.addEventListener('DOMContentLoaded', updateCartUI);
