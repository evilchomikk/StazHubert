package com.example.SpringStart.tables.cartproduct.model;

import com.example.SpringStart.tables.cart.model.Cart;
import com.example.SpringStart.tables.embedded.id.CartProductId;
import com.example.SpringStart.tables.product.model.Product;
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
@Table(name = "cart_product")
@Entity
@Audited
public class CartProduct {

    @EmbeddedId
    private CartProductId cartProductId;

    @ManyToOne
    @MapsId("id_product")
    @JoinColumn(name = "id_product")
    private Product product;

    @OneToOne
    @JoinColumn(name = "id_cart", insertable = false, updatable = false)
    private Cart cart;
    private int ammount;
}