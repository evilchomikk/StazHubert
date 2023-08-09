package org.example.generator.helpers;

import java.time.*;
import java.util.*;

public class FileFieldConverter {

    public Object stringToType(String value, Class<?> fieldType) {
        try {
            if (fieldType == int.class || fieldType == Integer.class) {
                return Integer.parseInt(value);
            } else if (fieldType == double.class || fieldType == Double.class) {
                return Double.parseDouble(value);
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                return Boolean.parseBoolean(value);
            } else if (fieldType == String.class) {
                return String.valueOf(value);
            } else if (fieldType == List.class) {
                String[] elementsArray = value.split(",");
                return new ArrayList<>(List.of(elementsArray));
            }
        } catch (Exception e) {
            throw new RuntimeException(e) {
                @Override
                public String getMessage() {
                    return "Error while converting string to type";
                }
            };
        }
        return null;
    }

    public Object stringToType(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            try {
                return Double.parseDouble(value);
            } catch (Exception e1) {
                try {
                    return LocalDate.parse(value);
                } catch (Exception e2) {
                    if (value.contains(",")) {
                        return stringToList(value);
                    } else if (value.equals("true") || value.equals("false")) {
                        return Boolean.parseBoolean(value);
                    } else {
                        return String.valueOf(value);
                    }
                }

            }
        }
    }

    public Object stringToList(String value) {
        String[] spliterators = value.split(",");
        List<String> list = new ArrayList<String>(List.of(spliterators));
        return list;
    }
}