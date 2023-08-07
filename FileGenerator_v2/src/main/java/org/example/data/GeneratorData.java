package org.example.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.annotations.SortBy;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorData {

    List<?> listOfObjects;


    public void sortValues(ClassFields classFields) {
        if (listOfObjects.isEmpty()) {
            return;
        }

        Field sortByField = classFields.getListOfClassFields().stream()
                .filter(field -> field.isAnnotationPresent(SortBy.class))
                .findFirst()
                .orElse(null);

        if (sortByField == null) {
            return;
        }

        sortByField.setAccessible(true);

        List<Object> mutableList = new ArrayList<>(listOfObjects);

        mutableList.sort((o1, o2) -> {
            try {
                Object fieldValue1 = sortByField.get(o1);
                Object fieldValue2 = sortByField.get(o2);

                if (fieldValue1 instanceof Comparable) {
                        return ((Comparable) fieldValue1).compareTo(fieldValue2);
                } else {
                    return 0;
                }

            } catch (Exception e ) {
                return -1;
            }
        });

   listOfObjects = mutableList;
    }




}



