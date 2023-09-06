package com.example.SpringStart.tables.orders.model;

import com.example.SpringStart.tables.customer.model.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "orders")
@Entity
@Audited
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "date_of_order")
    private LocalDate dateOfOrder;
}
