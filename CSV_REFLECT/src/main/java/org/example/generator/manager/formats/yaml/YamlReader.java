package org.example.generator.manager.formats.yaml;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.example.generator.data.ClassFields;
import org.example.generator.manager.filemanager.objectmappers.ObjectMapperReader;
import org.example.generator.manager.interfaces.Reader;

import java.util.List;

public class YamlReader implements Reader {

    private static final String FILETYPE = ".yaml";

    @Override
    public List read(Class clazz, String sourceLocation, ClassFields classFields) throws Exception {
        return new ObjectMapperReader(new YAMLMapper()).read(clazz, sourceLocation, FILETYPE);
    }
}

