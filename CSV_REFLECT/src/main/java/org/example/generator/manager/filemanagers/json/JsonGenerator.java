package org.example.generator.manager.filemanagers.json;

import org.example.annotations.DateFormat;
import org.example.annotations.IgnoreField;
import org.example.annotations.IgnoreInnerLists;
import org.example.annotations.NullsEquals;
import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.interfaces.Generator;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonGenerator implements Generator {
    @Override
    public void generate(String targetLoc, ClassFields classFields, GeneratorData generatorData) throws IOException {
        FileWriter file = new FileWriter(targetLoc);
        BufferedWriter buffer = new BufferedWriter(file);
        List<JSONObject> listOfJsonObjects = new ArrayList<>();

        generatorData.getListOfObjects().forEach(obj -> {
            JSONObject jsonObject = new JSONObject();
            classFields.getListOfClassFields().forEach(field -> {
                if (!field.isAnnotationPresent(IgnoreField.class)) {

                    Object fieldValue;
                    field.setAccessible(true);
                    try {
                        fieldValue = field.get(obj);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    if (field.getType().equals(LocalDate.class)) {
                        if (obj.getClass().isAnnotationPresent(DateFormat.class)) {
                            LocalDate date = (LocalDate) fieldValue;
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(obj.getClass().getAnnotation(DateFormat.class).dateFormat());
                            fieldValue = formatter.format(date);
                        }
                    }

                    if (fieldValue == null) {
                        if (obj.getClass().isAnnotationPresent(NullsEquals.class)) {
                            fieldValue = obj.getClass().getAnnotation(NullsEquals.class).nullValue();
                        }
                    }

                    if (field.getType().equals(List.class)) {
                        if (!obj.getClass().isAnnotationPresent(IgnoreInnerLists.class)) {
                            jsonObject.put(field.getName(), fieldValue);
                        }
                    } else {
                        jsonObject.put(field.getName(), fieldValue);
                    }

                }
            });
            listOfJsonObjects.add(jsonObject);
        });

        listOfJsonObjects.forEach(jsonObject -> {
            try {
                buffer.write(jsonObject.toJSONString() + "\r");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        buffer.close();
        file.close();
    }
}
