package org.example.generator.data;

import lombok.Getter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Getter
public class ClassFields {
    final List<Field> listOfClassFields;

    public ClassFields(Class clazz) {
        listOfClassFields = getFields(clazz);
    }

    List<Field> getFields(Class clazz) {

        List<Field> classFields = new ArrayList<>();
        List<Field> superClassFields;
        Stack<Field> superClassFieldsStack = new Stack<>();
        Class supeClass = clazz.getSuperclass();

        while (supeClass != Object.class) {
            superClassFields = new ArrayList<>(List.of(supeClass.getDeclaredFields()));
            Collections.reverse(superClassFields);
            superClassFields.forEach(superClassFieldsStack::push);
            supeClass = supeClass.getSuperclass();
        }


        while (!superClassFieldsStack.isEmpty()) {
            classFields.add(superClassFieldsStack.pop());
        }


        classFields.addAll(List.of(clazz.getDeclaredFields()));

        return classFields;

    }


}
