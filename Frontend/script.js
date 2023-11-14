$(document).ready(function () {
    var cartTotal = 0;
    var cartItems = 0;
    var cartPrice = $(".cart__price .total");
    var cartItemsTotal = $(".cart__items .total");

    $(".add-to-cart").on("click", function () {
        var price = parseFloat($(this).data("price"));
        cartTotal += price;
        cartItems++;

        cartPrice.text(cartTotal.toFixed(2));
        cartItemsTotal.text(cartItems);
    });

    $(".close").on("click", function (e) {
        e.preventDefault();
    });
});
