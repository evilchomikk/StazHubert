package org.example.filemanager;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.PoiGenerator;
import org.example.data.ClassFields;
import org.example.data.FileType;
import org.example.data.GeneratorData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class FileGenerator {

    public void generate(FileType type, List list, String targetLock) throws Exception {

        ClassFields classFields = new ClassFields(list.get(0).getClass());
        GeneratorData generatorData = new GeneratorData(list);
        generatorData.sortValues(classFields);

        FileWriter file;
        BufferedWriter buffer;
        String fileType;

            ObjectMapper mapper;
            switch (type) {

                case JSON -> {
                    mapper = new JsonMapper();
                    fileType = ".json";
                }
                case XML -> {
                    mapper = new XmlMapper();
                    fileType = ".xml";
                }
                case YAML -> {
                    mapper = new YAMLMapper();
                    fileType = ".yaml";
                }
                case CSV -> {
                    new PoiGenerator().generate("C:\\Users\\kulkah\\Desktop\\pliki\\test", classFields,generatorData);
                    fileType = ".csv";
                    return;
                }
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }

            file = new FileWriter(targetLock + fileType);
            buffer = new BufferedWriter(file);

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.setVisibility(mapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            mapper.registerModule(new JavaTimeModule());
            mapper.registerModule(new org.example.jacksonModules.DontGenerateModule());
            mapper.registerModule(new org.example.jacksonModules.NullsEqualsModule());
            mapper.registerModule(new org.example.jacksonModules.ColumnModule());
            mapper.registerModule(new org.example.jacksonModules.IgnoreListsModule());
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            mapper.writeValue(buffer, generatorData.getListOfObjects());

            buffer.close();
            file.close();






    }

}
