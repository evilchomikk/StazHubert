package org.example.filemanager;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.data.ClassFields;
import org.example.data.FileType;
import org.example.helpers.Converter;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.nio.file.Paths;
import java.util.*;

public class FileReader {

    public List read(FileType type, Class clazz, String sourceLock) throws Exception {

        List<Object> objectsFromFile = new ArrayList<>();
        ObjectMapper mapper;
        String fileType;

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
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new org.example.jacksonModules.DontGenerateModule());
        mapper.registerModule(new org.example.jacksonModules.NullsEqualsModule());
        mapper.registerModule(new org.example.jacksonModules.ColumnModule());
        mapper.registerModule(new org.example.jacksonModules.IgnoreListsModule());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        List<Object> objects = mapper.readValue(Paths.get(sourceLock+fileType).toFile(), new TypeReference<>() {
        });


        Converter converter = new Converter();
        Constructor[] constructor = clazz.getConstructors();

        for (Object object : objects) {
            Map<String,Object> map = (Map<String, Object>) object;
            List<Object> values =new ArrayList<>();

            map.values().forEach(value -> values.add(converter.stringToType(value.toString())));

            System.out.println(values);
            System.out.println(Arrays.toString(constructor[0].getParameterTypes()));

            object = constructor[0].newInstance(values.toArray());
            objectsFromFile.add(object);
        }


        return objectsFromFile;


    }
}


