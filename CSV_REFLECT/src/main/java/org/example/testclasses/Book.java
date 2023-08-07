package org.example.testclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String author;
    private int pages;

}
