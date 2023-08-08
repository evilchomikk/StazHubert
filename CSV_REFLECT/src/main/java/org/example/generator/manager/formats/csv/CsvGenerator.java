package org.example.generator.manager.formats.csv;

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

    private static final String SPLITTER = ";";
    private static final String FILETYPE = ".csv";
    private GeneratorData csvData;
    private ClassFields csvClassFields;

    @Override
    public void generate(String targetLocation, ClassFields classFields, GeneratorData generatorData) throws IOException {
        csvData = generatorData;
        csvClassFields = classFields;

        FileWriter fileWriter = new FileWriter(targetLocation + FILETYPE);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        writeColumnHeaders(bufferedWriter);
        writeRowValues(bufferedWriter);

        bufferedWriter.close();
        fileWriter.close();
    }

    public void writeColumnHeaders(BufferedWriter writer) throws IOException {
        StringBuilder columnHeader = new StringBuilder();

        csvClassFields.getListOfClassFields().forEach(field -> {
            if (isWritable(field))
                columnHeader.append(field.getName()).append(SPLITTER);
        });

        columnHeader.setLength(columnHeader.length() - 1);
        columnHeader.append("\r");

        writer.write(columnHeader.toString());
        System.out.println(columnHeader);
    }

    public void writeRowValues(BufferedWriter writer) {

        csvData.getListOfObjects().forEach(obj -> {
            writeRowFields(obj, writer);
        });
    }

    public void writeRowFields(Object obj, BufferedWriter writer) {
        StringBuilder stringBuilder = new StringBuilder();
        Object fieldValue = null;

        for (Field field : csvClassFields.getListOfClassFields()) {

            if (isWritable(field)) {
                try {
                    fieldValue = getFieldValue(field, obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                continue;
            }
            stringBuilder.append(fieldValue).append(SPLITTER);
        }

        if (stringBuilder.length() > 1) {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        stringBuilder.append("\r");
        try {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(stringBuilder);
    }

    private Object getFieldValue(Field field, Object obj) throws IllegalAccessException {
        field.setAccessible(true);
        Object fieldValue = field.get(obj);

        if (fieldValue == null) {
            fieldValue = field.getAnnotation(NullsEquals.class).nullValue();
        }

        if (field.getClass().isAnnotationPresent(DateFormat.class)) {
            LocalDate date = (LocalDate) fieldValue;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(field.getClass().getAnnotation(DateFormat.class).dateFormat());
            fieldValue = formatter.format(date);
        }
        return fieldValue;
    }

    private boolean isWritable(Field field) {
        if (field.isAnnotationPresent(DontGenerate.class))
            return false;
        else if (field.getType().equals(List.class) && csvData.getClass().isAnnotationPresent(IgnoreInnerLists.class))
            return false;
        else
            return true;
    }
}