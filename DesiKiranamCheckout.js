import React from 'react';

const DesiKiranamCheckout = ({ cartItems, onCheckout }) => {
  return (
    <div>
      <h2>Checkout</h2>
      <button onClick={() => onCheckout(cartItems)}>Proceed to Checkout</button>
    </div>
  );
};

export default DesiKiranamCheckout;
