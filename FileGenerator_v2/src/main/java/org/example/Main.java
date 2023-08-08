package org.example;

import org.example.data.FileType;
import org.example.filemanager.FileManager;
import org.example.testclasses.Animal;
import org.example.testclasses.Cat;
import org.example.testclasses.Employee;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Employee> listOfEmployee = List.of(new Employee("Jan", 1, List.of("Kierwoca", "Piekarz", "Policjant"),  LocalDate.of(120,11,1)),
                new Employee(null, null, List.of("1", "2", "3"), LocalDate.of(120,10,10)),
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






        FileManager fileManager = new FileManager();
        fileManager.generate(FileType.YAML, listOfEmployee, "C:\\Users\\kulkah\\Desktop\\pliki\\test");
        System.out.println(fileManager.read(FileType.YAML, Cat.class, "C:\\Users\\kulkah\\Desktop\\pliki\\test"));
    }
}