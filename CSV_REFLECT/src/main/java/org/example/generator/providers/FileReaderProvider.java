package org.example.generator.providers;

import org.example.generator.data.FileType;
import org.example.generator.manager.formats.csv.CsvReader;
import org.example.generator.manager.formats.excel.ExcelReader;
import org.example.generator.manager.formats.json.JsonReader;
import org.example.generator.manager.formats.xml.XmlReader;
import org.example.generator.manager.formats.yaml.YamlReader;
import org.example.generator.manager.interfaces.Reader;

public class FileReaderProvider {

    public Reader chooseReader(FileType type) {

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
                return new YamlReader();
            }
            default ->
                    throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}