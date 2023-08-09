package org.example.generator.manager.filemanager.objectmappers;

import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.*;
import org.example.generator.helpers.*;

import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;

public class ObjectMapperReader {

    private final List<Object> objectsFromFile = new ArrayList<>();
    private final ObjectMapper mapper;

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
           // map.values().forEach(value -> values.add(value));
            object = constructor[0].newInstance(values.toArray());
            objectsFromFile.add(object);
        }
        return objectsFromFile;
    }
}