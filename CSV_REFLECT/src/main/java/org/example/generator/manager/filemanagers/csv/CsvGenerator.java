package org.example.generator.manager.filemanagers.csv;

import org.example.annotations.*;
import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.interfaces.Generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CsvGenerator implements Generator {
    private final String SPLITTER = "; ";


    @Override
    public void generate(String targetLoc, ClassFields classFields, GeneratorData generatorData) throws IOException {
        GeneratorData csvData = generatorData;
        ClassFields csvClassFields = classFields;

        FileWriter scan = new FileWriter(targetLoc + ".csv");
        BufferedWriter writer = new BufferedWriter(scan);

        System.out.println();
        getColTitles(csvData, csvClassFields, writer);
        getRowValues(csvData, csvClassFields, writer);

        writer.close();
        scan.close();

    }


    public void getColTitles(GeneratorData csvData, ClassFields classFields, BufferedWriter writer) throws IOException {
        StringBuilder colTitle = new StringBuilder();


        classFields.getListOfClassFields().forEach(field -> {
            if (!csvData.getListOfObjects().get(0).getClass().isAnnotationPresent(DontGenerate.class))
                if (!field.isAnnotationPresent(IgnoreField.class))
                    if (csvData.getListOfObjects().get(0).getClass().isAnnotationPresent(IgnoreInnerLists.class)) {
                        if (!field.getType().equals(List.class))
                            colTitle.append(field.getName()).append(SPLITTER);
                    } else {
                        colTitle.append(field.getName()).append(SPLITTER);
                    }


        });


        colTitle.setLength(colTitle.length() - 2);
        colTitle.append("\r");

        writer.write(colTitle.toString());
        System.out.println(colTitle);


    }

    public void getRowValues(GeneratorData csvData, ClassFields classFields, BufferedWriter writer) {


        if (!csvData.getListOfObjects().get(0).getClass().isAnnotationPresent(DontGenerate.class))
            csvData.getListOfObjects().forEach(obj -> {
                StringBuilder sb = new StringBuilder();

                for (Field field : classFields.getListOfClassFields()) {
                    Object fieldValue;

                    try {
                        field.setAccessible(true);
                        fieldValue = field.get(obj);

                        if (csvData.getListOfObjects().get(0).getClass().isAnnotationPresent(NullsEquals.class))
                            if (fieldValue == null)
                                fieldValue = csvData.getListOfObjects().get(0).getClass().getAnnotation(NullsEquals.class).nullValue();


                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                    if (!field.isAnnotationPresent(IgnoreField.class))
                        if (field.getType().equals(List.class)) {
                            if (!obj.getClass().isAnnotationPresent(IgnoreInnerLists.class)) {
                                sb.append(fieldValue).append(SPLITTER);
                            }
                        } else if (field.getType().equals(LocalDate.class)) {
                            if (obj.getClass().isAnnotationPresent(DateFormat.class)) {
                                LocalDate date;
                                date = (LocalDate) fieldValue;
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(obj.getClass().getAnnotation(DateFormat.class).dateFormat());
                                fieldValue = formatter.format(date);
                                sb.append(fieldValue).append(SPLITTER);
                            } else {
                                sb.append(fieldValue).append(SPLITTER);
                            }
                        } else {
                            sb.append(fieldValue).append(SPLITTER);
                        }
                }


                if (sb.length() > 2) {
                    sb.setLength(sb.length() - 2);
                }
                sb.append("\r");
                try {
                    writer.write(sb.toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(sb);

            });
    }
}
