package org.example.generator.manager.formats.json;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.filemanager.objectmappers.ObjectMapperGenerator;
import org.example.generator.manager.interfaces.Generator;

public class JsonGenerator implements Generator {

    private static final String FILETYPE = ".json";

    @Override
    public void generate(String targetLocation, ClassFields classFields, GeneratorData generatorData) throws Exception {
        new ObjectMapperGenerator(new JsonMapper()).generate(targetLocation, generatorData, FILETYPE);
    }
}