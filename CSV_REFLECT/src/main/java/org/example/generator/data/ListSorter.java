package org.example.generator.data;

import org.example.annotations.SortBy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ListSorter {

    private List<?> listOfObjects;
    private ClassFields classFields;

    public ListSorter(List<?> listOfObjects, ClassFields classFields) {
        this.listOfObjects = listOfObjects;
        this.classFields = classFields;
    }

    public List<Object> sortValues() {
        if (listOfObjects.isEmpty()) {
            return null;
        }

        Field sortByField = classFields.getListOfClassFields().stream()
                .filter(field -> field.isAnnotationPresent(SortBy.class))
                .findFirst()
                .orElse(classFields.getListOfClassFields().get(0));

        if (sortByField == null) {
            return null;
        }

        sortByField.setAccessible(true);

        List<Object> mutableList = new ArrayList<>(listOfObjects);

        mutableList.sort((o1, o2) -> compareObjects(o1, o2, sortByField));

        return mutableList;
    }

    private int compareObjects(Object o1, Object o2, Field sortByField) {
        try {
            Object fieldValue1 = sortByField.get(o1);
            Object fieldValue2 = sortByField.get(o2);

            if (fieldValue1 instanceof Comparable) {
                return ((Comparable) fieldValue1).compareTo(fieldValue2);
            } else {
                return 0;
            }
        } catch (Exception e) {
            return -1;
        }
    }
}