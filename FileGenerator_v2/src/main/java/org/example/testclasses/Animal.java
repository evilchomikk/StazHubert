package org.example.testclasses;




public class Animal extends Living{

    String name;

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
