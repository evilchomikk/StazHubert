package org.example.generator.manager.filemanager.objectmappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.generator.helpers.FileFieldConverter;

import java.lang.reflect.Constructor;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ObjectMapperReader {

    List<Object> objectsFromFile = new ArrayList<>();
    ObjectMapper mapper;

    public ObjectMapperReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List read(Class clazz, String sourceLocation, String FILETYPE) throws Exception {
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new org.example.jacksonModules.DateFormatModule());
        mapper.registerModule(new org.example.jacksonModules.DontGenerateModule());
        mapper.registerModule(new org.example.jacksonModules.NullsEqualsModule());
        mapper.registerModule(new org.example.jacksonModules.IgnoreListsModule());
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        List<Object> objects = mapper.readValue(Paths.get(sourceLocation + FILETYPE).toFile(), new TypeReference<>() {
        });

        FileFieldConverter converter = new FileFieldConverter();
        Constructor[] constructor = clazz.getConstructors();

        for (Object object : objects) {
            Map<String, Object> map = (Map<String, Object>) object;
            List<Object> values = new ArrayList<>();
            map.values().forEach(value -> values.add(converter.stringToType(value.toString())));
            object = constructor[0].newInstance(values.toArray());
            objectsFromFile.add(object);
        }
        return objectsFromFile;
    }
}