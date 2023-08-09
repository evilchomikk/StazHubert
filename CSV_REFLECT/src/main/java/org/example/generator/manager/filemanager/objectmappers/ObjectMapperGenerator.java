package org.example.generator.manager.filemanager.objectmappers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.generator.data.GeneratorData;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ObjectMapperGenerator {

    private final ObjectMapper mapper;

    public ObjectMapperGenerator(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public void generate(String targetLocation, GeneratorData generatorData, String FILETYPE) throws Exception {
        try (FileWriter fileWriter = new FileWriter(targetLocation + FILETYPE);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.setVisibility(mapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            mapper.registerModule(new JavaTimeModule());
            mapper.registerModule(new org.example.jacksonModules.DateFormatModule());
            mapper.registerModule(new org.example.jacksonModules.DontGenerateModule());
            mapper.registerModule(new org.example.jacksonModules.NullsEqualsModule());
            mapper.registerModule(new org.example.jacksonModules.IgnoreListsModule());
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            mapper.writeValue(bufferedWriter, generatorData.getListOfObjects());
        }
    }
}