package com.example.SpringStart.tables.cartproduct.model;

import com.example.SpringStart.tables.cart.model.Cart;
import com.example.SpringStart.tables.embedded.id.CartProductId;
import com.example.SpringStart.tables.product.model.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CartProductDTO {

    private CartProductId cartProductId;
    private Product product;
    private Cart cart;
    private int ammount;
}
