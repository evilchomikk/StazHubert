package org.example.generator.manager.filemanager;


import org.example.generator.data.ClassFields;
import org.example.generator.data.FileType;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.formats.csv.CsvGenerator;
import org.example.generator.manager.formats.excel.ExcelGenerator;
import org.example.generator.manager.formats.json.JsonGenerator;
import org.example.generator.manager.formats.xml.XmlGenerator;
import org.example.generator.manager.formats.yaml.YamlGenerator;

import java.util.List;

public class FileGenerator {

    public void chooseGenerator(FileType type, List list, String targetLock) throws Exception {
        GeneratorData generatorData = new GeneratorData(list);
        ClassFields classFields = new ClassFields(list.get(0).getClass());
        generatorData.sortValues(classFields);

        switch (type) {
            case CSV -> {
                new CsvGenerator().generate(targetLock,classFields,generatorData);
            }
            case EXCEL -> {
                new ExcelGenerator().generate(targetLock,classFields,generatorData);
            }
            case JSON -> {
                new JsonGenerator().generate(targetLock,classFields,generatorData);
            }
            case XML -> {
                new XmlGenerator().generate(targetLock,classFields,generatorData);
            }
            case YAML -> {
                new YamlGenerator().generate(targetLock,classFields,generatorData);
            }
        }
    }

}
