package org.example.generator.manager.filemanager;

import org.example.generator.data.ClassFields;
import org.example.generator.data.FileType;
import org.example.generator.manager.filemanagers.csv.CsvReader;
import org.example.generator.manager.filemanagers.excel.ExcelReader;
import org.example.generator.manager.filemanagers.json.JsonReader;
import org.example.generator.manager.filemanagers.xml.XmlReader;
import org.example.generator.manager.filemanagers.yaml.YamlReader;

import java.util.List;

public class FileReader {

    public List chooseReader(FileType type, Class clazz, String sourceLock) throws Exception {
        ClassFields classFields = new ClassFields(clazz);

        switch (type) {
            case CSV -> {
                return new CsvReader().read(clazz, sourceLock, classFields);
            }
            case EXCEL -> {
                return new ExcelReader().read(clazz, sourceLock, classFields);
            }
            case JSON -> {
                return new JsonReader().read(clazz, sourceLock, classFields);
            }
            case XML -> {
                return new XmlReader().read(clazz, sourceLock, classFields);
            }
            case YAML -> {
                new YamlReader().read(clazz, sourceLock, classFields);
            }
        }


        return null;
    }
}


