function addToFavorites(productId, title, price, img, text, status) {
    let favorites = JSON.parse(localStorage.getItem('favorites')) || [];
    let product = favorites.find(p => p.id === productId);

    if (!product) {
        favorites.push({ id: productId, title: title, price: parseFloat(price), img: img, text: text, status: status });
        localStorage.setItem('favorites', JSON.stringify(favorites));
        showMessage(`Товар "${title}" додано до улюблених`);
        updateFavoritesUI();
    } else {
        showMessage(`Товар "${title}" вже є у списку улюблених`);
    }
}

function removeFromFavorites(productId) {
    let favorites = JSON.parse(localStorage.getItem('favorites')) || [];
    let index = favorites.findIndex(item => item.id === productId);

    if (index > -1) {
        favorites.splice(index, 1);
        localStorage.setItem('favorites', JSON.stringify(favorites));
        showMessage('Товар видалено з улюблених');
        updateFavoritesUI();
    }
}

function addToCart(productId, title, price, img, text) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let product = cart.find(p => p.id === productId);

    if (product) {
        product.quantity += 1;
    } else {
        cart.push({ id: productId, title: title, price: parseFloat(price), img: img, text: text, quantity: 1 });
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    showMessage(`Товар "${title}" додано до кошика`);
    updateCartUI();
    removeFromFavorites(productId);
}

function updateFavoritesUI() {
    let favorites = JSON.parse(localStorage.getItem('favorites')) || [];
    let favoritesHtml = favorites.map(item => `
        <div class="card">
            <img src="${item.img}" alt="Product Image" class="card-img-top">
            <div class="card-body">
                <h5 class="card-title">${item.title}</h5>
                <p class="card-text">${item.text}</p>
                <p class="card-price">Ціна: ₴${item.price}</p>
                <button class="btn btn-success" 
                        onclick="addToCart('${item.id}', '${item.title}', '${item.price}', '${item.img}', '${item.text}')"
                        ${item.status === 'not available' ? 'disabled' : ''}>
                    ${item.status === 'not available' ? 'Не доступно' : 'Додати до кошика'}
                </button>
                <button class="btn btn-danger" onclick="removeFromFavorites('${item.id}')">Видалити з улюблених</button>
            </div>
        </div>
    `).join('');

    let favoriteItems = document.getElementById('favoriteItems');
    if (favoriteItems) {
        if (favorites.length === 0) {
            favoriteItems.innerHTML = '<p>Список улюблених пустий :(</p>';
        } else {
            favoriteItems.innerHTML = favoritesHtml;
        }
    }
}

function showMessage(message) {
    let messageContainer = document.getElementById('messageContainer');
    if (!messageContainer) return;

    messageContainer.textContent = message;
    messageContainer.classList.remove('d-none');

    setTimeout(() => {
        messageContainer.classList.add('d-none');
    }, 3000);
}

document.addEventListener('DOMContentLoaded', () => {
    updateFavoritesUI();
});
