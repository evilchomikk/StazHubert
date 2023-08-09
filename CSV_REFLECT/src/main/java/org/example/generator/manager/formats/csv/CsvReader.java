package org.example.generator.manager.formats.csv;

import org.example.generator.data.ClassFields;
import org.example.generator.helpers.FileFieldConverter;
import org.example.generator.manager.interfaces.Reader;

import java.io.BufferedReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class CsvReader implements Reader {

    private final String SPLITTER = ";";

    @Override
    public List read(Class clazz, String sourceLoc, ClassFields classFields) throws Exception {

        java.io.FileReader scan = new java.io.FileReader(sourceLoc + ".csv");
        BufferedReader reader = new BufferedReader(scan);
        var line = reader.readLine();//omija 1 linie

        List<Object> newObjectsList = new ArrayList<>();
        Constructor<?>[] constructor = clazz.getConstructors();
        FileFieldConverter converter = new FileFieldConverter();

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