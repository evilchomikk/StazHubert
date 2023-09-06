package org.example.embedded;

import jakarta.persistence.Column;

public class OrdersProductId {
    @Column(name = "Id_Order")
    private int orderId;
    @Column(name = "Id_Product")
    private int productId;
}
