package org.example.generator.data;

import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class ClassFields {

    private final List<Field> listOfClassFields;
    public ClassFields(Class clazz) {
        listOfClassFields = getListOfClassFields(clazz);
    }

    private List<Field> getListOfClassFields(Class clazz) {
        return getListOfClassFields(getListOfSuperClasses(clazz));
    }

    private List<Class> getListOfSuperClasses(Class clazz){
        List<Class> listOfSuperClasses = new ArrayList<>();
        Class superClass = clazz.getSuperclass();
        while(superClass != Object.class){
            listOfSuperClasses.add(superClass);
            superClass = superClass.getSuperclass();
        }
        Collections.reverse(listOfSuperClasses);
        listOfSuperClasses.add(clazz);

        return listOfSuperClasses;
    }

    private List<Field> getListOfClassFields(List<Class> listOfClasses){
        List<Field> listOfFields = new ArrayList<>();
        for(Class clazz : listOfClasses){
            List<Field> superClassesFields = new ArrayList<>(List.of(clazz.getDeclaredFields()));
            listOfFields.addAll(superClassesFields);
            Collections.reverse(superClassesFields);
        }
        return listOfFields;

    }
}