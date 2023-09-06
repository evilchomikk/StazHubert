package com.example.SpringStart.tables.embedded.id;

import jakarta.persistence.Column;

public class CartProductId {
    @Column(name = "Id_Cart")
    private int cartId;
    @Column(name = "Id_Product")
    private int productId;
}
