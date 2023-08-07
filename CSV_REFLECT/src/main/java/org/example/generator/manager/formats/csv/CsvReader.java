package org.example.generator.manager.formats.csv;

import org.example.generator.data.ClassFields;
import org.example.generator.helpers.Converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {
    private final String SPLITTER = "; ";


    public List read(Class clazz, String sourceLoc, ClassFields classFields) throws IOException, InvocationTargetException, InstantiationException, IllegalAccessException {

        java.io.FileReader scan = new java.io.FileReader(sourceLoc + ".csv");
        BufferedReader reader = new BufferedReader(scan);
        var line = reader.readLine();//omija 1 linie

        List<Object> newObjectsList = new ArrayList<>();
        Constructor<?>[] constructor = clazz.getConstructors();
        Converter converter = new Converter();

        while ((line = reader.readLine()) != null) {
            String[] splitedLine = line.split(SPLITTER);
            Object[] objects = new Object[splitedLine.length];

            for (int i = 0; i < classFields.getListOfClassFields().size(); i++) {
                objects[i] = converter.stringToType(splitedLine[i], classFields.getListOfClassFields().get(i).getType());
            }

            newObjectsList.add(constructor[0].newInstance(objects));
        }
        reader.close();
        return newObjectsList;
    }
}
