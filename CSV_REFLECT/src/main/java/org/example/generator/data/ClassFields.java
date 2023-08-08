package org.example.generator.data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ClassFields {

    final private List<Field> listOfClassFields;

    public ClassFields(Class clazz) {
        listOfClassFields = getFields(clazz);
    }

    public List<Field> getListOfClassFields() {
        return listOfClassFields;
    }

    private List<Field> getFields(Class clazz) {

        List<Field> classFields = new ArrayList<>();
        Stack<Field> superClassFieldsStack = new Stack<>();
        Class supeClass = clazz.getSuperclass();

        while (supeClass != Object.class) {
            List<Field> superClassFields = new ArrayList<>(List.of(supeClass.getDeclaredFields()));
            Collections.reverse(superClassFields);
            superClassFields.forEach(superClassFieldsStack::push);
            supeClass = supeClass.getSuperclass();
        }

        // classFields.addAll(new ArrayList<>(superClassFieldsStack));
        while (!superClassFieldsStack.isEmpty()) {
            classFields.add(superClassFieldsStack.pop());
        }

        classFields.addAll(List.of(clazz.getDeclaredFields()));

        return classFields;
    }
}