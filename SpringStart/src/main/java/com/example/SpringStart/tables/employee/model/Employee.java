package com.example.SpringStart.tables.employee.model;

import com.example.SpringStart.tables.embedded.fileds.Name;
import com.example.SpringStart.tables.logindata.model.LoginData;
import com.example.SpringStart.tables.role.model.Role;
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
    private Integer id;

    @Embedded
    private Name name;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @OneToOne
    @JoinColumn(name = "id_login_data")
    private LoginData loginData;
}