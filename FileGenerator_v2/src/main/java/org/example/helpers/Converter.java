package org.example.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {
    public Object stringToType( String value,Class<?> fieldType) {
        try {
            if (fieldType == int.class || fieldType == Integer.class) {
                return Integer.parseInt(value);
            } else if (fieldType == double.class || fieldType == Double.class) {
                return Double.parseDouble(value);
            } else if (fieldType == boolean.class || fieldType == Boolean.class) {
                return Boolean.parseBoolean(value);
            } else if (fieldType == String.class) {
                return String.valueOf(value);
            } else if(fieldType == List.class){
                String[] spliterators = value.split(",");
                List list = new ArrayList<>(Arrays.asList(spliterators));
                return list;
            }else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    public Object stringToType(String value){
        try {
            return Integer.parseInt(value);
        }catch (Exception e){
            try {
                return Double.parseDouble(value);
            }catch (Exception e1){
                if(value.equals("true")||value.equals("false"))
                    return Boolean.parseBoolean(value);
                else
                    return String.valueOf(value);

            }
        }
    }

}
