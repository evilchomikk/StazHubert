package com.example.SpringStart.tables.cart.model;


import com.example.SpringStart.tables.customer.model.Customer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "cart")
@Audited
public class Cart {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
}