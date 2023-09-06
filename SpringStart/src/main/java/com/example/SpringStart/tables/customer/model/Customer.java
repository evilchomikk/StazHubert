package com.example.SpringStart.tables.customer.model;


import com.example.SpringStart.tables.embedded.fileds.Name;
import com.example.SpringStart.tables.logindata.model.LoginData;
import com.example.SpringStart.tables.orders.model.Orders;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.envers.Audited;

import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "customer")
@Audited
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Name name;

    @OneToOne
    @JoinColumn(name = "id_login_data")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private LoginData loginData;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;


}