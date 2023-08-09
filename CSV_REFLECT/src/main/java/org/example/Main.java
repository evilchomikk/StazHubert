package org.example;

import org.example.generator.data.*;
import org.example.generator.manager.filemanager.*;
import org.example.testclasses.*;

import java.time.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        List<Employee> listOfEmployee = List.of(new Employee("Jan", 1, List.of("Kierwoca", "Piekarz", "Policjant"),  LocalDate.of(120,11,1)),
                new Employee("Krzysztof", null, List.of("1", "2", "3"), LocalDate.of(120,10,10)),
                new Employee("Hubert", 4, List.of("a", "b", "c"),  LocalDate.of(120,10,10)),
                new Employee("Jan", 3, List.of("Piekarz", "1", "a"),  LocalDate.of(120,10,10))

        );

         List<Book> listOfBooks = List.of(
                new Book("Pan Tadeusz", "Adam Mickiewicz", 1834),
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", 1911),
                new Book("Krzyżacy", "Henryk Sienkiewicz", 1900),
                new Book("Dziady", "Adam Mickiewicz", 1823),
                new Book("Lalka", "Bolesław Prus", 1890)
        );

        List<Animal> listOfAnimals = List.of(
                new Cat(true,"Pies", 4, "Black"),
                new Cat(true,"Kangur", 2,"Blue"),
                new Cat(true,"Kot", 4,"Cyan"),
                new Cat(false,"Chomik", 4,"Grey"),
                new Cat(false,"Niewiem", 3,"Green")
        );


        FileManager.getInstance().generate(FileType.EXCEL,listOfEmployee,"C:\\Users\\kulkah\\Desktop\\pliki\\jakasnazwa");
        System.out.println( FileManager.getInstance().read(FileType.EXCEL,Employee.class,"C:\\Users\\kulkah\\Desktop\\pliki\\jakasnazwa"));
    }
}