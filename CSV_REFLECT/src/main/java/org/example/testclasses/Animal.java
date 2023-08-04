package org.example.testclasses;


import org.example.annotations.SortBy;

public class Animal extends Living{

    String name;
@SortBy
    int numberOfLegs;

    public Animal(boolean lives, String name, int numberOfLegs) {
        super(lives);
        this.name = name;
        this.numberOfLegs = numberOfLegs;
    }


    public Animal() {
        super();
    }
}
