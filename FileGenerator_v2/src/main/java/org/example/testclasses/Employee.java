package org.example.testclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @NullsEquals(nullValue = "STAS")
    @Column(3)
    @SortBy
    private String name;
    @Column(1)
    private Integer id;
    List<String> role;
    LocalDate dateOfEmployment;

}
