package com.example.SpringStart.tables.product.model;

import com.example.SpringStart.tables.cartproduct.model.CartProduct;
import com.example.SpringStart.tables.ordersproduct.model.OrdersProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.util.List;

@NoArgsConstructor
@ToString
@Setter
@Getter
@Table(name = "product")
@Audited
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @Column(precision = 2)
    private float price;

    private int ammount;
    private float vat;
    private String category;

    @OneToMany(mappedBy = "product", orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<OrdersProduct> ordersProducts;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @ToString.Exclude
    @JsonIgnore
    private List<CartProduct> cartProducts;
}
