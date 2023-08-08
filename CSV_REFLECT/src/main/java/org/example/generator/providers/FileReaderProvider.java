package org.example.generator.providers;

import org.example.generator.data.FileType;
import org.example.generator.manager.formats.csv.CsvReader;
import org.example.generator.manager.formats.excel.ExcelReader;
import org.example.generator.manager.formats.json.JsonReader;
import org.example.generator.manager.formats.xml.XmlReader;
import org.example.generator.manager.formats.yaml.YamlReader;
import org.example.generator.manager.interfaces.Reader;

public class FileReaderProvider {

    public Reader chooseReader(FileType type) throws Exception {
        try {
            switch (type) {
                case CSV -> {
                    return new CsvReader();
                }
                case EXCEL -> {
                    return new ExcelReader();
                }
                case JSON -> {
                    return new JsonReader();
                }
                case XML -> {
                    return new XmlReader();
                }
                case YAML -> {
                    new YamlReader();
                }
            }
        } catch (Exception e) {
            System.out.println("Nie obsługiwany Typ");
        }
        return null;
    }
}