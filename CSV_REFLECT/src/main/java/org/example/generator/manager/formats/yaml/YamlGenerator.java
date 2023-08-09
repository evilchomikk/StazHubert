package org.example.generator.manager.formats.yaml;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.example.generator.data.ClassFields;
import org.example.generator.data.GeneratorData;
import org.example.generator.manager.filemanager.objectmappers.ObjectMapperGenerator;
import org.example.generator.manager.interfaces.Generator;

public class YamlGenerator implements Generator {

    private static final String FILETYPE = ".yaml";
    @Override
    public void generate(String targetLocation, ClassFields classFields, GeneratorData generatorData) throws Exception {
        new ObjectMapperGenerator(new YAMLMapper()).generate(targetLocation, generatorData, FILETYPE);
    }
}