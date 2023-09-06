package org.example.tables;


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
    private int id;

    private String street;
    private String city;

    @Column(name = "Zip_Code")
    private String ZipCode;

    @Column(name = "Base_Address")
    private boolean BaseAddress;

    @ManyToOne
    @JoinColumn(name = "Id_Customer")
    private Customer customer;
}