package org.example.tables;


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
import org.example.embedded.OrdersProductId;
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
    @MapsId("Id_Product")
    @JoinColumn(name = "Id_Product")
    private Product product;

    @OneToOne
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private Orders order;

    private int ammount;
    private float price;
}