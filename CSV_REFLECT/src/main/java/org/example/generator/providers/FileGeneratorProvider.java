package org.example.generator.providers;

import org.example.generator.data.FileType;
import org.example.generator.manager.formats.csv.CsvGenerator;
import org.example.generator.manager.formats.excel.ExcelGenerator;
import org.example.generator.manager.formats.json.JsonGenerator;
import org.example.generator.manager.formats.xml.XmlGenerator;
import org.example.generator.manager.formats.yaml.YamlGenerator;
import org.example.generator.manager.interfaces.Generator;

public class FileGeneratorProvider {

    public Generator chooseGenerator(FileType type) {
        try {
            switch (type) {
                case CSV -> {
                    return new CsvGenerator();
                }
                case EXCEL -> {
                    return new ExcelGenerator();
                }
                case JSON -> {
                    return new JsonGenerator();
                }
                case XML -> {
                    return new XmlGenerator();
                }
                case YAML -> {
                    return new YamlGenerator();
                }
            }
        } catch (Exception e) {
            System.out.println("Nie obsługiwany Typ");
        }
        return null;
    }
}
