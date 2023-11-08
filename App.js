import React, { useState } from 'react';
import DesiKiranamCart from './DesiKiranamCart';
import DesiKiranamCheckout from './DesiKiranamCheckout';

function App() {
  const [cartItems, setCartItems] = useState([]);
  
  const handleAddToCart = (item) => {
    setCartItems([...cartItems, item]);
  };

  const handleRemoveFromCart = (index) => {
    const updatedCart = [...cartItems];
    updatedCart.splice(index, 1);
    setCartItems(updatedCart);
  };

  const handleCheckout = (cartItems) => {
    // Send cartItems to the server for order creation
    // We will make an API request to your server here

    // but for now we'll just log the cart items
    console.log('Cart Items:', cartItems);

    // Clear the cart after the order is created
    setCartItems([]);
  };
  return (
    <div>
      <h1>Desi Kiranam</h1>
      <DesiKiranamCart cartItems={cartItems} onRemoveItem={handleRemoveFromCart} />
      <DesiKiranamCheckout cartItems={cartItems} onCheckout={handleCheckout} />
    </div>
  );
}

export default App;
