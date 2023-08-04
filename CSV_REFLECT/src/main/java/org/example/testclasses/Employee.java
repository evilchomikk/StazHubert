package org.example.testclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.annotations.*;

import java.time.LocalDate;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@NullsEquals(nullValue = "NIEDZIAŁA TUTAJ")
@DateFormat(dateFormat = "yyyy-MM-dd")
public class Employee {

    private String name;
    @SortBy
    private Integer id;
    List<String> role;
    LocalDate dateOfEmployment;

}
