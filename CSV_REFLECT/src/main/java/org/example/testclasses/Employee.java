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

public class Employee {

    private String name;
    @NullsEquals(nullValue = "0")
    @SortBy
    private Integer id;
    List<String> role;
    @DateFormat(dateFormat = "yyyy-MM-dd")
    LocalDate dateOfEmployment;

}
