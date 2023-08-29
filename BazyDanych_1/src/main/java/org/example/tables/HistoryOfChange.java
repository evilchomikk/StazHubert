package org.example.tables;


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
    private int id;

    @OneToOne
    @JoinColumn(name = "Id_Product")
    private Product product;

    @Column(name = "Old_Price")
    private float oldPrice;

    @Column(name = "New_Price")
    private float newPrice;

    @Column(name = "Date_Of_Change")
    private LocalDate dateOfChange;

    @OneToOne
    @JoinColumn(name = "Id_Employee")
    private Employee employee;
}
