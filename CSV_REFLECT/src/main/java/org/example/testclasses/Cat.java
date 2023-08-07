package org.example.testclasses;

//@Data

public class Cat extends Animal {
    @Override
    public String toString() {
        return "Cat{" +
                "color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", numberOfLegs=" + numberOfLegs +
                '}';
    }

    String color;

    public Cat(boolean lives, String name, int numberOfLegs, String color) {
        super(lives, name, numberOfLegs);
        this.color = color;
    }

    public Cat() {
        super();
    }


}
