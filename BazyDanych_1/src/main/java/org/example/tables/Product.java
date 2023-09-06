package org.example.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "product")
@Audited
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private float price;
    private int ammount;
    private float vat;
    private String category;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<OrdersProduct> ordersProducts;

    @OneToMany(mappedBy = "product")
    private List<CartProduct> cartProducts;
}
