package org.example.testclasses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.annotations.DontGenerate;
import org.example.annotations.SortBy;

@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String author;
    private int pages;

}
