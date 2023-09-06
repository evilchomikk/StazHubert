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
import org.example.embedded.CartProductId;
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
    @MapsId("Id_Product")
    @JoinColumn(name = "Id_Product")
    private Product product;

    @OneToOne
    @JoinColumn(name = "Id_Cart", insertable = false, updatable = false)
    private Cart cart;
    private int ammount;
}