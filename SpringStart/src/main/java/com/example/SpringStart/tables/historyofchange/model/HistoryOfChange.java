package com.example.SpringStart.tables.historyofchange.model;


import com.example.SpringStart.tables.employee.model.Employee;
import com.example.SpringStart.tables.product.model.Product;
import jakarta.persistence.Column;
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

import java.time.LocalDate;

@NoArgsConstructor
@Setter
@Getter
@Table(name = "history_of_change")
@Entity
@Audited
public class HistoryOfChange {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column(name = "old_price")
    private float oldPrice;

    @Column(name = "new_price")
    private float newPrice;

    @Column(name = "date_of_change")
    private LocalDate dateOfChange;

    @OneToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;
}
