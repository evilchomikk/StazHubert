package org.example;

import org.example.generator.data.FileType;
import org.example.generator.manager.filemanager.FileManager;
import org.example.testclasses.Animal;
import org.example.testclasses.Book;
import org.example.testclasses.Cat;
import org.example.testclasses.Employee;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
    public List<Book> listOfBooks = List.of(
            new Book("Pan Tadeusz", "Adam Mickiewicz", 1834),
            new Book("W pustyni i w puszczy", "Henryk Sienkiewicz", 1911),
            new Book("Krzyżacy", "Henryk Sienkiewicz", 1900),
            new Book("Dziady", "Adam Mickiewicz", 1823),
            new Book("Lalka", "Bolesław Prus", 1890)
    );
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        List<Employee> listOfEmployee = List.of(new Employee("Jan", 1, List.of("Kierwoca", "Piekarz", "Policjant"),  LocalDate.of(120,11,1)),
                new Employee("Krzysztof", null, List.of("1", "2", "3"), LocalDate.of(120,10,10)),
                new Employee("Hubert", 4, List.of("a", "b", "c"),  LocalDate.of(120,10,10)),
                new Employee("Jan", 3, List.of("Piekarz", "1", "a"),  LocalDate.of(120,10,10))

        );



        List<Animal> listOfAnimals = List.of(
                new Cat(true,"Pies", 4, "Black"),
                new Cat(true,"Kangur", 2,"Blue"),
                new Cat(true,"Kot", 4,"Cyan"),
                new Cat(false,"Chomik", 4,"Grey"),
                new Cat(false,"Niewiem", 3,"Green")
        );








        FileManager generator = new FileManager();
        generator.generate(FileType.XML,listOfAnimals,"C:\\Users\\kulkah\\Desktop\\pliki\\jakasnazwa2");
        System.out.println( generator.read(FileType.XML,Book.class,"C:\\Users\\kulkah\\Desktop\\pliki\\jakasnazwa2"));
    }
}