package org.example.tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQueries({
        @NamedQuery(name = "Trial.findByName", query = "select t from Trial t where t.name = :name")
})
@NoArgsConstructor
@Getter
@Setter
public class Trial {
    @Id
    private int id;

    private String name;
}