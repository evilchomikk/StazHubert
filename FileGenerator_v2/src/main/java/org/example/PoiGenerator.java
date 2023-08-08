package org.example;

import com.opencsv.CSVWriter;
import org.example.data.ClassFields;
import org.example.data.GeneratorData;

import java.io.File;
import java.io.IOException;

public class PoiGenerator {
    public void generate(String targetLoc, ClassFields classFields, GeneratorData generatorData) throws IOException {

        File file = new File(targetLoc + ".csv");
        CSVWriter writer = new CSVWriter(new java.io.FileWriter(file));

        String[] header = classFields.getListOfClassFields().stream().map(field -> field.getName()).toArray(String[]::new);
        writer.writeNext(header);

        generatorData.getListOfObjects().forEach(object -> {
            String[] row = classFields.getListOfClassFields().stream().map(field -> {
                field.setAccessible(true);
                try {
                    return field.get(object).toString();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return null;
            }).toArray(String[]::new);
            writer.writeNext(row);
        });
        writer.close();


    }
}
