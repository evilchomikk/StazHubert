package org.example.tables;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.example.embedded.Name;
import org.hibernate.envers.Audited;

@NoArgsConstructor
@Setter
@ToString
@Audited
@Getter
@Entity
@Table(name = "employee")
@NamedQueries({
        @NamedQuery(name = "Employee.findByRole_Name", query = "select e from Employee e where e.role.name = :name ")
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private Name name;

    @ManyToOne
    @JoinColumn(name = "Id_Role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "Id_Login_Data")
    private LoginData loginData;
}