package com.example.SpringStart.tables.ordersproduct.model;


import com.example.SpringStart.tables.embedded.id.OrdersProductId;
import com.example.SpringStart.tables.orders.model.Orders;
import com.example.SpringStart.tables.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "orders_product")
@Audited
public class OrdersProduct {

    @EmbeddedId
    private OrdersProductId id;

    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product")
    private Product product;

    @OneToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private Orders order;

    private int ammount;
    @Column(precision = 2)
    private float price;
}