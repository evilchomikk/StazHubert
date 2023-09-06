package com.example.SpringStart.tables.address.model;


import com.example.SpringStart.tables.customer.model.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "address")
@Audited
public class Address {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    private String street;
    private String city;

    @Column(name = "zip_code")
    private String ZipCode;

    @Column(name = "base_address")
    private boolean BaseAddress;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;
}